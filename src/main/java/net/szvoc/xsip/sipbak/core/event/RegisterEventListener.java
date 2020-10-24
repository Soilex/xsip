package net.szvoc.xsip.sipbak.core.event;

import lombok.var;
import net.szvoc.xsip.sipbak.core.*;
import net.szvoc.xsip.sipbak.core.authentication.AuthenticateService;
import net.szvoc.xsip.sipbak.core.header.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RegisterEventListener {
    @Autowired
    private AuthenticateService authenticateService;

    @EventListener(condition = "#event.direction == T(net.szvoc.xsip.sipbak.core.InOutFlag).IN && #event.message.method() == T(net.szvoc.xsip.sipbak.core.SipMethod).REGISTER")
    public void onEvent(SipEvent event) {
        var request = event.getMessage();
        var connection = event.getSource();

        var authorization = request.header(HeaderName.AUTHORIZATION, AuthorizationHeader.class);
        var expires = request.header(HeaderName.SESSION_EXPIRES, ExpiresHeader.class);

        var status = expires == null || expires.intValue() <= 0 ? SipStatus.OK :
                authenticateService.authenticate(authorization);
        var authentication = status.isOk() ? null : new AuthorizationHeader.Builder()
                .www(true)
                .scheme("Digest")
                .realm(SipOptions.REALM)
                .nonce(Nonce.randomString())
                .algorithm("MD5")
                .qop("auth")
                .build();

        var header = new HeaderSpec.Builder()
                .header(request.headers(HeaderName.VIA))
                .header(request.header(HeaderName.FROM, FromHeader.class))
                .header(request.header(HeaderName.TO, ToHeader.class))
                .header(request.header(HeaderName.CALL_ID, CallIDHeader.class))
                .header(request.header(HeaderName.CSEQ, CSeqHeader.class))
                .header(new UserAgentHeader(SipOptions.USER_AGENT))
                .header(new AllowHeader(SipOptions.ALLOWS))
                .header(authentication)
                .header(new SupportedHeader(SipOptions.ABILITES))
                .header(new ContentLengthHeader(0))
                .build();
        var response = new Response(connection.localAddress(), request.sender(), SipOptions.VERSION, status, header, "");
        connection.send(response);
    }
}
