package com.test.julyOld.service.api_auth_access_token.model;

import com.test.julyOld.entity.ApiAuthAccessToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiAuthAccessTokenRequest {
    private ApiAuthAccessToken token;
}
