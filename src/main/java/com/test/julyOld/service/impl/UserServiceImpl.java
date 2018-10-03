package com.test.julyOld.service.impl;

import com.test.julyOld.entity.APIUserDetail;
import com.test.julyOld.entity.Role;
import com.test.julyOld.entity.User;
import com.test.julyOld.repository.APIUserDetailRepository;
import com.test.julyOld.repository.UserRepository;
import com.test.julyOld.service.RoleService;
import com.test.julyOld.service.UserService;
import com.test.julyOld.service.exception.EntityNotFoundException;
import com.test.julyOld.service.model.userRequsts.UserCreationRequest;
import com.test.julyOld.service.model.userRequsts.UserModificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.apache.commons.lang.Validate.notEmpty;
import static org.springframework.util.Assert.notNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private APIUserDetailRepository apiUserDetailRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public User create(final UserCreationRequest userCreationRequest) {
        notNull(userCreationRequest, "userCreationRequest can not be null");
        final String name = userCreationRequest.getName();
        final String userName = userCreationRequest.getUserName();
        final String password = userCreationRequest.getPassword();
        final String roleId = userCreationRequest.getRoleId();
        validate(name, userName, password, roleId);

        final Role newRole = roleService.get(roleId);

        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(newRole);
        User newUser = new User();
        newUser.setName(name);
        newUser = userRepository.save(newUser);

        final APIUserDetail apiUserDetail = new APIUserDetail();

        apiUserDetail.setUsername(userName);
        apiUserDetail.setPasswordHash(password);
        apiUserDetail.setApproved(true);
        apiUserDetail.setEnabled(true);
        apiUserDetail.setRoles(rolesSet);
        apiUserDetail.setUser(newUser);
        apiUserDetailRepository.save(apiUserDetail);
        return newUser;
    }

    @Override
    public User update(UserModificationRequest userModificationRequest) {
        notNull(userModificationRequest, "userCreationDto can not be null");
        final String id = userModificationRequest.getId();
        User existingUser = get(id);

        final String name = userModificationRequest.getName();
        final String userName = userModificationRequest.getUserName();
        final String password = userModificationRequest.getPassword();

        if (!name.isEmpty()){
            existingUser.setName(name);
            existingUser = userRepository.save(existingUser);
        }
        if (!userName.isEmpty() || !password.isEmpty()){
            final APIUserDetail apiUserDetail = apiUserDetailRepository.findByUser_IdAndDeletedFalse(id);
            if (!userName.isEmpty()) apiUserDetail.setUsername(userName);
            if (!password.isEmpty()) apiUserDetail.setPasswordHash(password);
            apiUserDetailRepository.save(apiUserDetail);
        }
        return existingUser;
    }

    @Override
    public User get(String id) {
        notNull(id, "user request id can not be null");
        final User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new EntityNotFoundException();
        return user;
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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return apiUserDetailRepository.findByUsernameAndDeletedFalse(userName);
    }
}
