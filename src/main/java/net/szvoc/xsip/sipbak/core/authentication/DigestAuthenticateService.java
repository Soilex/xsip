package net.szvoc.xsip.sipbak.core.authentication;

import net.szvoc.xsip.sipbak.core.SipStatus;
import net.szvoc.xsip.sipbak.core.header.AuthorizationHeader;
import org.springframework.stereotype.Component;

@Component
public class DigestAuthenticateService implements AuthenticateService {

    @Override
    public SipStatus authenticate(AuthorizationHeader authorizationHeader) {
        return authorizationHeader == null ? SipStatus.UNAUTHORIZED : SipStatus.OK;
    }
}
