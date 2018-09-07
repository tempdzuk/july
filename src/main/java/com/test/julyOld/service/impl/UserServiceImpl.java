package com.test.julyOld.service.impl;

import com.test.julyOld.endpoint.model.UserDto;
import com.test.julyOld.entity.Role;
import com.test.julyOld.entity.User;
import com.test.julyOld.repository.UserRepository;
import com.test.julyOld.service.UserService;
import com.test.julyOld.service.exception.EntityNotFoundException;
import com.test.julyOld.service.model.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang.Validate.notEmpty;
import static org.springframework.util.Assert.notNull;

@Service
public class UserServiceImpl implements UserService {

    public static boolean defaultsAreSet = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;

    @Override
    public List<User> getUsersByRole(String roleName) {
        notEmpty(roleName, "role name can not be empty");
        final Role role = roleService.getRoleByName(roleName);
        final List<User> users = userRepository.findAllByRole(role);
        return users;
    }

    @Override
    public User create(final UserCreationRequest userCreationRequest) {
        notNull(userCreationRequest, "userCreationRequest can not be null");
        final String name = userCreationRequest.getName();
        final String userName = userCreationRequest.getUserName();
        final String password = userCreationRequest.getPassword();
        final String roleName = userCreationRequest.getRoleName();

        notEmpty(name, "name can not be empty");
        notEmpty(userName, "userName can not be empty");
        notEmpty(password, "password can not be empty");
        notEmpty(roleName, "role name can not be empty");
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
        Long userId = userDto.getId();
        notNull(userId, "user id can not be null");
        if (userRepository.findById(userId).isPresent()){
            String name = userDto.getName();
            String userName = userDto.getUserName();
            String password = userDto.getPassword();
            notEmpty(name, "can not be empty");
            notEmpty(userName, "user name can not be empty");
            notEmpty(password, "password can not be empty");
            User user = userRepository.findById(userId).get();
            user.setName(name);
            user.setUserName(userName);
            user.setPassword(password);
            return userRepository.save(user);
        }else throw new EntityNotFoundException();
    }

    @Override
    public User getUserById(Long id) {
        notNull(id, "user id can not be null");
        if (userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }else throw new EntityNotFoundException();
    }

    @Override
    public User getUserByUserName(String userName) {
        notEmpty(userName, "userName can not be empty");
        if (!userRepository.existsByUserName(userName)) throw new EntityNotFoundException();
        final User user = userRepository.findByUserName(userName);
        return user;
    }

    @Override
    public List<User> findAll() {
        final List<User> users = userRepository.findAll();
        notEmpty(users, "users can not be empty");
        return users;
    }
}
