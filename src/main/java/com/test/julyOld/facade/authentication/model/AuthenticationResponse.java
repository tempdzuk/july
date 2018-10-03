package com.test.julyOld.facade.authentication.model;

import com.test.julyOld.entity.APIUserDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private APIUserDetail apiUserDetail;
    private String token;
}
