package com.test.julyOld.endpoint;


import com.test.julyOld.endpoint.component.TaskConverter;
import com.test.julyOld.endpoint.model.TaskDto;
import com.test.julyOld.entity.Task;
import com.test.julyOld.service.impl.TaskServiceImpl;
import com.test.julyOld.service.model.TaskCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "task")
public class TaskEndpoint {

    @Autowired
    private TaskConverter taskConverter;

    @Autowired
    private TaskServiceImpl taskService;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<TaskDto> getAllTasks(){
        final List<Task> tasks = taskService.getAll();
        return taskConverter.convertEntityToDtoList(tasks);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public TaskDto create(@RequestBody final TaskDto taskDto){
        final TaskCreationRequest taskCreationRequest = taskConverter.convertDtoToRequest(taskDto);
        final Task createdTask = taskService.create(taskCreationRequest);
        return taskConverter.convertEntityToDto(createdTask);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "")
    public TaskDto update(@RequestBody final TaskDto taskDto){
        final Task updatedTask = taskService.update(taskDto);
        return taskConverter.convertEntityToDto(updatedTask);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public Map<String, List<TaskDto>> getAllTasksByUser(@PathVariable final Long userId){
        final Map<String, List<Task>> tasks = taskService.getAllTasksByUser(userId);
        return taskConverter.convertEntityToDtoMap(tasks);
    }
}
