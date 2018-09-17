package com.test.julyOld.endpoint.component;


import com.test.julyOld.endpoint.model.ProjectDto;
import com.test.julyOld.endpoint.model.TaskDto;
import com.test.julyOld.entity.Project;
import com.test.julyOld.repository.ProjectRepository;
import com.test.julyOld.service.model.ProjectCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ProjectConverter {

    @Autowired
    private TaskConverter taskConverter;

    public ProjectCreationRequest convertDtoToRequest(final ProjectDto projectDto){
        final String name = projectDto.getName();
        final String description = projectDto.getDescription();
        final ProjectCreationRequest projectCreationRequest = new ProjectCreationRequest();

        projectCreationRequest.setName(name);
        projectCreationRequest.setDescription(description);
        return projectCreationRequest;
    }

    public ProjectDto convertEntityToDto(final Project project){
        final String name = project.getName();
        final String description = project.getDescription();
        final Long id = project.getId();
        final Set<TaskDto> tasks = taskConverter.convertEntityToDtoSet(project.getTasks());
        final ProjectDto projectDto = new ProjectDto();

        projectDto.setId(id);
        projectDto.setName(name);
        projectDto.setDescription(description);
        projectDto.setTasks(tasks);
        return projectDto;
    }

    public List<ProjectDto> convertEntityToDtoList(final List<Project> projects){
        final List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project: projects) {
            projectDtoList.add(convertEntityToDto(project));
        }
        return projectDtoList;
    }

}
