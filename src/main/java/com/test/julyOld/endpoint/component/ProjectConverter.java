package com.test.julyOld.endpoint.component;


import com.test.julyOld.endpoint.model.ProjectDto;
import com.test.julyOld.entity.Project;
import com.test.julyOld.repository.ProjectRepository;
import com.test.julyOld.service.model.ProjectCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectConverter {

    public ProjectCreationRequest convertDtoToRequest(final ProjectDto projectDto){
        String projectName = projectDto.getName();
        String projectDescription = projectDto.getDescription();
        ProjectCreationRequest projectCreationRequest = new ProjectCreationRequest();
        projectCreationRequest.setName(projectName);
        projectCreationRequest.setDescription(projectDescription);
        return projectCreationRequest;
    }

    public ProjectDto convertEntityToDto(final Project project){
        String projectName = project.getName();
        String projectDescription = project.getDescription();
        Long projectId = project.getId();
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(projectId);
        projectDto.setName(projectName);
        projectDto.setDescription(projectDescription);
        return projectDto;
    }

    public List<ProjectDto> convertEntityToDtoList(final List<Project> projects){
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for (Project project: projects) {
            projectDtoList.add(convertEntityToDto(project));
        }
        return projectDtoList;
    }

}
