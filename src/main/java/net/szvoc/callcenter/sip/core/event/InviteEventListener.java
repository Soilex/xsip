package net.szvoc.callcenter.sip.core.event;

import lombok.var;
import net.szvoc.callcenter.sip.core.*;
import net.szvoc.callcenter.sip.core.header.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class InviteEventListener {

    @EventListener(condition = "#event.direction == T(net.szvoc.callcenter.sip.core.InOutFlag).IN && #event.message.method() == T(net.szvoc.callcenter.sip.core.SipMethod).INVITE")
    public void onEvent(SipEvent event) throws InterruptedException {
        var request = event.getMessage();
        var connection = event.getSource();

        var status = SipStatus.TRYING;
        var header = new HeaderSpec.Builder()
                .header(request.headers(HeaderName.VIA))
                .header(request.header(HeaderName.FROM, FromHeader.class))
                .header(request.header(HeaderName.TO, ToHeader.class))
                .header(request.header(HeaderName.CALL_ID, CallIDHeader.class))
                .header(request.header(HeaderName.CSEQ, CSeqHeader.class))
                .header(new UserAgentHeader(SipOptions.USER_AGENT))
                .header(new AllowHeader(SipOptions.ALLOWS))
                .header(new ContentLengthHeader(0))
                .build();
        var response = new Response(connection.localAddress(), request.sender(), SipOptions.VERSION, status, header, "");
        connection.send(response);

        TimeUnit.SECONDS.sleep(3);
        status = SipStatus.RINGING;
        header = new HeaderSpec.Builder()
                .header(request.headers(HeaderName.VIA))
                .header(request.header(HeaderName.FROM, FromHeader.class))
                .header(request.header(HeaderName.TO, ToHeader.class))
                .header(request.header(HeaderName.CALL_ID, CallIDHeader.class))
                .header(request.header(HeaderName.CSEQ, CSeqHeader.class))
                .header(new UserAgentHeader(SipOptions.USER_AGENT))
                .header(new AllowHeader(SipOptions.ALLOWS))
                .header(new ContentLengthHeader(0))
                .build();
        response = new Response(connection.localAddress(), request.sender(), SipOptions.VERSION, status, header, "");
        connection.send(response);

        TimeUnit.SECONDS.sleep(10);
        status = SipStatus.OK;
        header = new HeaderSpec.Builder()
                .header(request.headers(HeaderName.VIA))
                .header(request.header(HeaderName.FROM, FromHeader.class))
                .header(request.header(HeaderName.TO, ToHeader.class))
                .header(request.header(HeaderName.CALL_ID, CallIDHeader.class))
                .header(request.header(HeaderName.CSEQ, CSeqHeader.class))
                .header(new UserAgentHeader(SipOptions.USER_AGENT))
                .header(new AllowHeader(SipOptions.ALLOWS))
                .header(new ContentLengthHeader(0))
                .build();
        response = new Response(connection.localAddress(), request.sender(), SipOptions.VERSION, status, header, "");
        connection.send(response);
    }
}
