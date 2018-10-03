package com.test.julyOld.service.impl;

import com.test.julyOld.entity.Role;
import com.test.julyOld.repository.RoleRepository;
import com.test.julyOld.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static org.apache.commons.lang.Validate.notEmpty;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role get(final String roleId) {
        notEmpty(roleId, "role id can not be empty");
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        final List<Role> roles = roleRepository.findAll();
        notEmpty(roles, "There is no role created");
        return roles;
    }
}
