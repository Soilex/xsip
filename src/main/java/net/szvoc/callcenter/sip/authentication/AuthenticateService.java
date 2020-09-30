package net.szvoc.callcenter.sip.authentication;

import net.szvoc.callcenter.sip.SipStatus;
import net.szvoc.callcenter.sip.header.AuthorizationHeader;

public interface AuthenticateService {

    SipStatus authenticate(AuthorizationHeader authorizationHeader);
}
