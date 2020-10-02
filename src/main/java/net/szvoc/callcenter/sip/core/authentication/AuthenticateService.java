package net.szvoc.callcenter.sip.core.authentication;

import net.szvoc.callcenter.sip.core.SipStatus;
import net.szvoc.callcenter.sip.core.header.AuthorizationHeader;

public interface AuthenticateService {

    SipStatus authenticate(AuthorizationHeader authorizationHeader);
}
