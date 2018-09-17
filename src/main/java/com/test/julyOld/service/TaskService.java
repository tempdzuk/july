package com.test.julyOld.service;

import com.test.julyOld.endpoint.model.TaskDto;
import com.test.julyOld.entity.Task;
import com.test.julyOld.service.model.TaskCreationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TaskService {

    Task create(TaskCreationRequest taskCreationRequest);

    Task update(TaskDto taskDto);

    List<Task> getAll();

    Map<String, List<Task>> getAllTasksByUser(Long userId);

    Task getById(Long id);
}
