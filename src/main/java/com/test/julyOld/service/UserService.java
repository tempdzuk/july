package com.test.julyOld.service;

import com.test.julyOld.entity.User;
import com.test.julyOld.service.model.userRequsts.UserCreationRequest;
import com.test.julyOld.service.model.userRequsts.UserModificationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    User create(UserCreationRequest userCreationRequest);

    User update(UserModificationRequest userModificationRequest);

    User get(String id);

    List<User> getAll();

}
