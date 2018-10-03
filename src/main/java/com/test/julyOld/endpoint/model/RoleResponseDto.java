package com.test.julyOld.endpoint.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleResponseDto {

    @NotNull
    private String id;

    @NotNull
    private String roleName;

}
