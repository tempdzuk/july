package com.test.julyOld.service.user_detail;

import com.test.julyOld.entity.APIUserDetail;
import com.test.julyOld.entity.User;
import com.test.julyOld.service.user_detail.model.ChangePasswordRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ApiUserDetailService extends UserDetailsService {

    APIUserDetail loadUserByUsername(String username) throws UsernameNotFoundException;

    APIUserDetail changePassword(ChangePasswordRequest changePasswordRequest);

    APIUserDetail changePassword(String userId, String newPassword);

    APIUserDetail changeEmail(String userId, String newEmail);

    boolean isEmailUsed(String email);

    boolean isCorrectPassword(String userId, String password);

    boolean isUserActive(User user);
}
