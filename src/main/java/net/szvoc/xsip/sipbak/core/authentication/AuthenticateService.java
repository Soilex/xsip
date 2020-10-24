package net.szvoc.xsip.sipbak.core.authentication;

import net.szvoc.xsip.sipbak.core.SipStatus;
import net.szvoc.xsip.sipbak.core.header.AuthorizationHeader;

public interface AuthenticateService {

    SipStatus authenticate(AuthorizationHeader authorizationHeader);
}
