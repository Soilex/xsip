package net.szvoc.callcenter.sip.authentication;

import net.szvoc.callcenter.sip.SipStatus;
import net.szvoc.callcenter.sip.header.AuthorizationHeader;
import org.springframework.stereotype.Component;

@Component
public class DigestAuthenticateService implements AuthenticateService {

    @Override
    public SipStatus authenticate(AuthorizationHeader authorizationHeader) {
        return authorizationHeader == null ? SipStatus.UNAUTHORIZED : SipStatus.OK;
    }
}
