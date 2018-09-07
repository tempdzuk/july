package com.test.julyOld.endpoint;

import com.test.julyOld.endpoint.component.RoleConverter;
import com.test.julyOld.endpoint.model.RoleDto;
import com.test.julyOld.entity.Role;
import com.test.julyOld.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "role")
public class RoleEndpoint {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private RoleConverter roleConverter;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<RoleDto> getAllRoles(){
        final List<Role> roles = roleService.findAll();
        return roleConverter.convertEntityToDtoList(roles);
    }


}
