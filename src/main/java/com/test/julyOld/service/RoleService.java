package com.test.julyOld.service;

import com.test.julyOld.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String roleName);

    List<Role> findAll();
}
