package com.test.julyOld.endpoint.component;

import com.test.julyOld.endpoint.model.TaskDto;
import com.test.julyOld.entity.Task;
import com.test.julyOld.service.model.TaskCreationRequest;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskConverter {

    public TaskCreationRequest convertDtoToRequest(final TaskDto taskDto){
        String story = taskDto.getStory();
        String description = taskDto.getDescription();
        Long projectId = taskDto.getProjectId();
        Long userId = taskDto.getUserId();
        TaskCreationRequest taskCreationRequest = new TaskCreationRequest();
        taskCreationRequest.setStory(story);
        taskCreationRequest.setDescription(description);
        taskCreationRequest.setProjectId(projectId);
        taskCreationRequest.setUserId(userId);
        return taskCreationRequest;
    }

    public TaskDto convertEntityToDto(Task task){
        Long taskId = task.getId();
        Long projectId = task.getProject().getId();
        String story = task.getStory();
        String description = task.getDescription();
        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskId);
        taskDto.setProjectId(projectId);
        if (task.getUser() != null) {
            Long userId = task.getUser().getId();
            taskDto.setUserId(userId);
        }
        taskDto.setStory(story);
        taskDto.setDescription(description);
        return taskDto;
    }

    public List<TaskDto> convertEntityToDtoList(List<Task> tasks){
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task:tasks) {
            taskDtoList.add(convertEntityToDto(task));
        }
        return taskDtoList;
    }

    public Map<String, List<TaskDto>> convertEntityToDtoMap(Map<String, List<Task>> tasks){
        Map<String, List<TaskDto>> taskDtoMap = new HashMap<>();
        Set<String> iterator = tasks.keySet();
        for (String key:iterator) {
            taskDtoMap.put(key, convertEntityToDtoList(tasks.get(key)));
        }
        return taskDtoMap;
    }
}
