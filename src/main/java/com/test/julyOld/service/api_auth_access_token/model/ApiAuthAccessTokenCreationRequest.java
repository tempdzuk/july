package com.test.julyOld.service.api_auth_access_token.model;

import com.test.julyOld.entity.APIUserDetail;
import com.test.julyOld.misc.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiAuthAccessTokenCreationRequest {
    private APIUserDetail userDetail;
    private TokenType tokenType;
    private boolean isActive;
    private Date expires;
}
