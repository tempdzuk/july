package com.test.julyOld.service.impl;

import com.test.julyOld.endpoint.model.TaskDto;
import com.test.julyOld.entity.Project;
import com.test.julyOld.entity.Task;
import com.test.julyOld.repository.TaskRepository;
import com.test.julyOld.service.TaskService;
import com.test.julyOld.service.exception.EntityNotFoundException;
import com.test.julyOld.service.model.TaskCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang.Validate.notEmpty;
import static org.springframework.util.Assert.notNull;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Task getById(Long id) {
        notNull(id, "id can not be null");
        final Task task = taskRepository.findById(id).orElse(null);
        if (task == null) throw new EntityNotFoundException();
        return task;
    }

    @Override
    public Task create(TaskCreationRequest taskCreationRequest) {
        notNull(taskCreationRequest, "taskCreationRequest can not be null");
        final String story = taskCreationRequest.getStory();
        final String description = taskCreationRequest.getDescription();
        final Long userId = taskCreationRequest.getUserId();
        final Long projectId = taskCreationRequest.getProjectId();
        validate(story, description, projectId);

        final Project project = projectService.getById(projectId);
        final Task newTask = new Task();
        newTask.setStory(story);
        newTask.setDescription(description);
        newTask.setProject(project);
        if (userId != null){
            newTask.setUser(userService.getById(userId));
        }
        return taskRepository.save(newTask);
    }

    @Override
    public Task update(TaskDto taskDto) {
        notNull(taskDto, "taskDto can not be null");
        final Long id = taskDto.getId();
        final Task existingTask = getById(id);

        final String story = taskDto.getStory();
        final String description = taskDto.getDescription();
        final Long projectId = taskDto.getProjectId();
        final Long userId = taskDto.getUserId();
        if (!story.isEmpty()) existingTask.setStory(story);
        if (!description.isEmpty()) existingTask.setDescription(description);
        if (projectId != null) existingTask.setProject(projectService.getById(projectId));
        if (userId != null) existingTask.setUser(userService.getById(userId));

        return taskRepository.save(existingTask);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Map<String, List<Task>> getAllTasksByUser(Long userId) {
        notNull(userId, "user id can not be null");

        if (!userService.exists(userId)) throw new EntityNotFoundException();

        final List<Task> tasks = taskRepository.findAllByUser_Id(userId);
        final Set<Project> projects = new HashSet<>();
        for (Task task:tasks) {
            projects.add(task.getProject());
        }
        final Map<String, List<Task>> tasksByProject = new HashMap<>();
        for (Project project:projects) {
            ArrayList<Task> tempTasks = new ArrayList<>();
            String projectName = project.getName();
            for (Task t:tasks) {
                if (t.getProject().getId().equals(project.getId())){
                    tempTasks.add(t);
                }
            }
            tasksByProject.put(projectName, tempTasks);
        }
        return tasksByProject;
    }

    private void validate(String story, String description, Long projectId){
        notEmpty(story, "task story can not be empty");
        notEmpty(description, "task description can not be empty");
        notNull(projectId, "project id can not be null");
    }
}
