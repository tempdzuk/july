package com.test.julyOld.service.impl;

import com.test.julyOld.entity.Role;
import com.test.julyOld.repository.RoleRepository;
import com.test.julyOld.service.RoleService;
import com.test.julyOld.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang.Validate.notEmpty;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleByName(final String roleName) {
        notEmpty(roleName, "roleName can not be empty");
        if (!roleRepository.existsByRoleName(roleName)) throw new EntityNotFoundException();
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public List<Role> findAll() {
        final List<Role> roles = roleRepository.findAll();
        notEmpty(roles, "There is no role created");
        return roles;
    }
}
