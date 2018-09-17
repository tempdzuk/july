package com.test.julyOld.service.impl;

import com.test.julyOld.endpoint.model.UserDto;
import com.test.julyOld.entity.Role;
import com.test.julyOld.entity.User;
import com.test.julyOld.repository.UserRepository;
import com.test.julyOld.service.UserService;
import com.test.julyOld.service.exception.EntityNotFoundException;
import com.test.julyOld.service.model.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang.Validate.notEmpty;
import static org.springframework.util.Assert.notNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User create(final UserCreationRequest userCreationRequest) {
        notNull(userCreationRequest, "userCreationRequest can not be null");
        final String name = userCreationRequest.getName();
        final String userName = userCreationRequest.getUserName();
        final String password = userCreationRequest.getPassword();
        final String roleName = userCreationRequest.getRoleName();
        validate(name, userName, password, roleName);

        final Role role = roleService.getRoleByName(roleName);
        final User newUser = new User(role);
        newUser.setName(name);
        newUser.setUserName(userName);
        newUser.setPassword(password);
        return userRepository.save(newUser);
    }

    @Override
    public User update(UserDto userDto) {
        notNull(userDto, "userDto can not be null");
        final Long id = userDto.getId();
        final User existingUser = getById(id);

        final String name = userDto.getName();
        final String userName = userDto.getUserName();
        final String password = userDto.getPassword();
        validate(name, userName, password);

        existingUser.setName(name);
        existingUser.setUserName(userName);
        existingUser.setPassword(password);
        return userRepository.save(existingUser);
    }

    @Override
    public User getById(Long id) {
        notNull(id, "user id can not be null");
        final User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new EntityNotFoundException();
        return user;
    }

    @Override
    public User getByUserName(String userName) {
        notEmpty(userName, "userName can not be empty");
        if (!userRepository.existsByUserName(userName)) throw new EntityNotFoundException();
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getAll() {
        final List<User> users = userRepository.findAll();
        notEmpty(users, "users can not be empty");
        return users;
    }

    private void validate(String name, String userName, String password, String roleName){
        notEmpty(name, "name can not be empty");
        notEmpty(userName, "userName can not be empty");
        notEmpty(password, "password can not be empty");
        notEmpty(roleName, "role name can not be empty");
    }

    private void validate(String name, String userName, String password){
        notEmpty(name, "name can not be empty");
        notEmpty(userName, "userName can not be empty");
        notEmpty(password, "password can not be empty");
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
