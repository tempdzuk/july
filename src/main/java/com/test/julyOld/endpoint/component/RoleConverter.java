package com.test.julyOld.endpoint.component;

import com.test.julyOld.endpoint.model.RoleDto;
import com.test.julyOld.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleConverter {

    public List<RoleDto> convertEntityToDtoList(final List<Role> roles){
        final List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role: roles) {
            roleDtoList.add(convertEntityToDto(role));
        }
        return roleDtoList;
    }

    public RoleDto convertEntityToDto(final Role role){
        final String name = role.getRoleName();
        final Long id = role.getId();
        RoleDto roleDto = new RoleDto();
        roleDto.setId(id);
        roleDto.setRoleName(name);
        return roleDto;
    }
}
