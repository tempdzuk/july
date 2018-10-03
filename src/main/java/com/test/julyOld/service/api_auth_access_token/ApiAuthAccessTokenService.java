package com.test.julyOld.service.api_auth_access_token;

import com.test.julyOld.entity.ApiAuthAccessToken;
import com.test.julyOld.service.api_auth_access_token.model.ApiAuthAccessTokenCreationRequest;
import com.test.julyOld.service.api_auth_access_token.model.ApiAuthAccessTokenRequest;
import java.util.Optional;

public interface ApiAuthAccessTokenService {

    Optional<ApiAuthAccessToken> findByToken(String token);

    Optional<ApiAuthAccessToken> findByUserDetailId(String userDetailId);

    ApiAuthAccessToken createApiAccessToken(ApiAuthAccessTokenCreationRequest request);

    ApiAuthAccessToken refreshApiAccessToken(ApiAuthAccessTokenRequest request);

    void inactivateApiAccessToken(ApiAuthAccessTokenRequest request);

    void deleteApiAccessToken(ApiAuthAccessTokenRequest request);
}
