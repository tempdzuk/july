package com.test.julyOld.endpoint;

import com.test.julyOld.endpoint.component.TaskConverter;
import com.test.julyOld.endpoint.component.UserConverter;
import com.test.julyOld.endpoint.model.task.TaskModificationDto;
import com.test.julyOld.endpoint.model.task.TaskResponseDto;
import com.test.julyOld.endpoint.model.user.UserModificationDto;
import com.test.julyOld.endpoint.model.user.UserResponseDto;
import com.test.julyOld.entity.APIUserDetail;
import com.test.julyOld.entity.Task;
import com.test.julyOld.entity.User;
import com.test.julyOld.service.TaskService;
import com.test.julyOld.service.UserService;
import com.test.julyOld.service.model.taskRequests.TaskModificationRequest;
import com.test.julyOld.service.model.userRequsts.UserModificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = "/user")
public class UserEndpoint {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskConverter taskConverter;

    @Autowired
    private TaskService taskService;

    // region USERS

    @RequestMapping(method = RequestMethod.GET, value = "users")
    public UserResponseDto getUser(){
        final APIUserDetail apiUserDetail = (APIUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(apiUserDetail.getUser().getId());
        userResponseDto.setName(apiUserDetail.getUser().getName());
        return userResponseDto;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "users")
    public UserResponseDto updateUser(@RequestBody final UserModificationDto userModificationDto){
        final APIUserDetail apiUserDetail = (APIUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userModificationDto.setId(apiUserDetail.getUser().getId());
        final UserModificationRequest userModificationRequest = userConverter.convertModificationDtoToRequest(userModificationDto);
        final User updatedUser = userService.update(userModificationRequest);
        return userConverter.convertEntityToDto(updatedUser);
    }

    //endregion

    // region TASKS

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public Map<String, List<TaskResponseDto>> getAllTasks(){
        final APIUserDetail apiUserDetail = (APIUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String userId = apiUserDetail.getUser().getId();
        final Map<String, List<Task>> tasks = taskService.getAllByUser(userId);
        return taskConverter.convertEntityToDtoMap(tasks);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "tasks")
    public TaskResponseDto updateTask(@RequestBody final TaskModificationDto taskModificationDto){
        final APIUserDetail apiUserDetail = (APIUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String userId = apiUserDetail.getUser().getId();
        final TaskModificationRequest taskModificationRequest = taskConverter.convertModificationDtoToRequest(taskModificationDto);
        final Task updatedTask = taskService.update(taskModificationRequest, userId);
        return taskConverter.convertEntityToDto(updatedTask);
    }

    // endregion
}
