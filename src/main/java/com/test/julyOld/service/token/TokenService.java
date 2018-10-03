package com.test.julyOld.service.token;

import com.test.julyOld.service.api_auth_access_token.model.ApiAuthAccessTokenCreationRequest;

public interface TokenService {

    String create(ApiAuthAccessTokenCreationRequest request);

    String refresh(final String token);

    String getUserDetailId(final String token);
}
