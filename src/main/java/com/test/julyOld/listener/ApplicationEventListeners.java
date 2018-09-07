package com.test.julyOld.listener;

import com.test.julyOld.entity.Project;
import com.test.julyOld.entity.Role;
import com.test.julyOld.entity.User;
import com.test.julyOld.repository.ProjectRepository;
import com.test.julyOld.repository.RoleRepository;
import com.test.julyOld.repository.UserRepository;
import com.test.julyOld.service.impl.RoleServiceImpl;
import com.test.julyOld.service.impl.UserServiceImpl;
import com.test.julyOld.service.model.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class ApplicationEventListeners {

    private static boolean def = false;

    @Value("${application.adminRole}")
    private String adminRoleName;

    @Value("${application.userRole}")
    private String userRoleName;

    @Value("${application.adminName}")
    private String adminName;

    @Value("${application.adminUserName}")
    private String adminUserName;

    @Value("${application.adminPassword}")
    private String adminPassword;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    @EventListener({ContextRefreshedEvent.class})
        public void onContextRefreshedEvent() {
            if (userRepository.findAll().size() == 0) {
                System.out.println("mtav");
                roleRepository.save(new Role(adminRoleName));
                roleRepository.save(new Role(userRoleName));
                final UserCreationRequest adminCreationRequest = new UserCreationRequest();
                adminCreationRequest.setName(adminName);
                adminCreationRequest.setUserName(adminUserName);
                adminCreationRequest.setPassword(adminPassword);
                adminCreationRequest.setRoleName(adminRoleName);
                userService.create(adminCreationRequest);
            }else {
                System.out.println("CHmtav");
            }
        }

//    @EventListener({ContextRefreshedEvent.class})

}
