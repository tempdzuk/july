package com.test.julyOld.endpoint.model;

import com.test.julyOld.entity.Role;

import javax.validation.constraints.NotNull;

public class UserDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
