package com.test.julyOld.endpoint.component;

import com.test.julyOld.endpoint.model.TaskDto;
import com.test.julyOld.entity.Task;
import com.test.julyOld.service.model.TaskCreationRequest;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskConverter {

    public TaskCreationRequest convertDtoToRequest(final TaskDto taskDto){
        final String story = taskDto.getStory();
        final String description = taskDto.getDescription();
        final Long projectId = taskDto.getProjectId();
        final Long userId = taskDto.getUserId();

        final TaskCreationRequest taskCreationRequest = new TaskCreationRequest();
        taskCreationRequest.setStory(story);
        taskCreationRequest.setDescription(description);
        taskCreationRequest.setProjectId(projectId);
        taskCreationRequest.setUserId(userId);

        return taskCreationRequest;
    }

    public TaskDto convertEntityToDto(Task task){
        final Long id = task.getId();
        final Long projectId = task.getProject().getId();
        final String story = task.getStory();
        final String description = task.getDescription();
        final TaskDto taskDto = new TaskDto();

        taskDto.setId(id);
        taskDto.setProjectId(projectId);
        taskDto.setStory(story);
        taskDto.setDescription(description);
        if (task.getUser() != null) {
            final Long userId = task.getUser().getId();
            taskDto.setUserId(userId);
        }

        return taskDto;
    }

    public List<TaskDto> convertEntityToDtoList(List<Task> tasks){
        final List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task:tasks) {
            taskDtoList.add(convertEntityToDto(task));
        }
        return taskDtoList;
    }

    public Set<TaskDto> convertEntityToDtoSet(Set<Task> tasks){
        Set<TaskDto> taskDtoSet = new HashSet<>();
        if (tasks != null) {
            for (Task task : tasks) {
                taskDtoSet.add(convertEntityToDto(task));
            }
        }
        return taskDtoSet;
    }

    public Map<String, List<TaskDto>> convertEntityToDtoMap(Map<String, List<Task>> tasks){
        final Map<String, List<TaskDto>> taskDtoMap = new HashMap<>();
        final Set<String> iterator = tasks.keySet();
        for (String key:iterator) {
            taskDtoMap.put(key, convertEntityToDtoList(tasks.get(key)));
        }
        return taskDtoMap;
    }
}
