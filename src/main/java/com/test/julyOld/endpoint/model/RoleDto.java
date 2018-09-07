package com.test.julyOld.endpoint.model;

import javax.validation.constraints.NotNull;

public class RoleDto {

    private Long id;

    @NotNull
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
