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
    public Task create(TaskCreationRequest taskCreationRequest) {
        notNull(taskCreationRequest, "taskCreationRequest can not be null");
        final String story = taskCreationRequest.getStory();
        final String description = taskCreationRequest.getDescription();
        final Long userId = taskCreationRequest.getUserId();
        final Long projectId = taskCreationRequest.getProjectId();
        notEmpty(story, "task story can not be empty");
        notEmpty(description, "task description can not be empty");
        notNull(projectId, "project id can not be null");
        final Task task = new Task();
        final Project project = projectService.getProjectById(projectId);
        task.setStory(story);
        task.setDescription(description);
        task.setProject(project);
        if (userId != null){
            task.setUser(userService.getUserById(userId));
        }
        return taskRepository.save(task);
    }

    @Override
    public Task update(TaskDto taskDto) {
        notNull(taskDto, "taskDto can not be null");
        final Long taskId = taskDto.getId();
        notNull(taskId, "taskDto can not be null");
        if (taskRepository.findById(taskId).isPresent()){
            final Task task = taskRepository.findById(taskId).get();
            final String story = taskDto.getStory();
            final String description = taskDto.getDescription();
            final Long projectId = taskDto.getProjectId();
            final Long userId = taskDto.getUserId();
            if (!story.isEmpty()) task.setStory(story);
            if (!description.isEmpty()) task.setDescription(description);
            if (projectId != null) task.setProject(projectService.getProjectById(projectId));
            if (userId != null) task.setUser(userService.getUserById(userId));
            return taskRepository.save(task);
        }else throw new EntityNotFoundException();
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        notEmpty(tasks, "Tasks are empty");
        return tasks;
    }

    @Override
    public List<Task> getAllTasksWithinProject(Long projectId) {
        notNull(projectId, "project id can not be null");
        if (taskRepository.existsByProject_Id(projectId)) return taskRepository.findAllByProject_Id(projectId);
        throw new EntityNotFoundException();
    }

    @Override
    public Map<String, List<Task>> getAllTasksByUser(Long userId) {
        notNull(userId, "user id can not be null");
        if (taskRepository.existsByUser_Id(userId)){
            List<Task> tasks = taskRepository.findAllByUser_Id(userId);
            Set<Project> projects = new HashSet<>();
            for (Task task:tasks) {
                projects.add(task.getProject());
            }
            Map<String, List<Task>> tasksByProject = new HashMap<>();
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
        throw new EntityNotFoundException();
    }
}
