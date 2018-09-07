package com.test.julyOld.endpoint.component;

import com.test.julyOld.endpoint.model.RoleDto;
import com.test.julyOld.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleConverter {

    public List<RoleDto> convertEntityToDtoList(final List<Role> projects){
        List<RoleDto> projectDtoList = new ArrayList<>();
        for (Role role: projects) {
            projectDtoList.add(convertEntityToDto(role));
        }
        return projectDtoList;
    }

    public RoleDto convertEntityToDto(final Role role){
        String roleName = role.getRoleName();
        Long roleId = role.getId();
        RoleDto roleDto = new RoleDto();
        roleDto.setId(roleId);
        roleDto.setRoleName(roleName);
        return roleDto;
    }
}
