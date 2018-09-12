package com.test.julyOld.service;


import com.test.julyOld.endpoint.model.UserDto;
import com.test.julyOld.entity.User;
import com.test.julyOld.service.model.UserCreationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User create(UserCreationRequest userCreationRequest);

    User update(UserDto userDto);

    User getUserById(Long id);

    User getUserByUserName(String userName);

    List<User> getUsersByRole(String roleName);

    List<User> findAll();

}
