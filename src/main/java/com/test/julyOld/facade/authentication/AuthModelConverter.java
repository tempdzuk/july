package com.test.julyOld.facade.authentication;

import com.test.julyOld.entity.APIUserDetail;
import com.test.julyOld.misc.TokenType;
import com.test.julyOld.service.api_auth_access_token.model.ApiAuthAccessTokenCreationRequest;
import java.util.Date;

public class AuthModelConverter {
    public static ApiAuthAccessTokenCreationRequest convert(final APIUserDetail userDetail, final boolean isRememberMe) {
        final ApiAuthAccessTokenCreationRequest apiAuthAccessTokenCreationRequest = new ApiAuthAccessTokenCreationRequest();
        apiAuthAccessTokenCreationRequest.setUserDetail(userDetail);
        apiAuthAccessTokenCreationRequest.setTokenType(isRememberMe ? TokenType.LOGIN_REMEMBER_ME : TokenType.LOGIN);
        apiAuthAccessTokenCreationRequest.setActive(true);
        apiAuthAccessTokenCreationRequest.setExpires(new Date());
        return apiAuthAccessTokenCreationRequest;
    }
}
