package net.szvoc.callcenter.sip.core.authentication;

import net.szvoc.callcenter.sip.core.SipStatus;
import net.szvoc.callcenter.sip.core.header.AuthorizationHeader;
import org.springframework.stereotype.Component;

@Component
public class DigestAuthenticateService implements AuthenticateService {

    @Override
    public SipStatus authenticate(AuthorizationHeader authorizationHeader) {
        return authorizationHeader == null ? SipStatus.UNAUTHORIZED : SipStatus.OK;
    }
}
