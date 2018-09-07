package com.test.julyOld.service.impl;

import com.test.julyOld.endpoint.model.ProjectDto;
import com.test.julyOld.entity.Project;
import com.test.julyOld.repository.ProjectRepository;
import com.test.julyOld.service.exception.EntityNotFoundException;
import com.test.julyOld.service.model.ProjectCreationRequest;
import com.test.julyOld.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang.Validate.notEmpty;
import static org.springframework.util.Assert.notNull;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project create(final ProjectCreationRequest projectCreationRequest) {
        notNull(projectCreationRequest, "projectCreationRequest can not be null");
        final String projectName = projectCreationRequest.getName();
        final String projectDescription = projectCreationRequest.getDescription();
        notEmpty(projectName, "project name can not be empty");
        notEmpty(projectDescription, "project description can not be empty");

        final Project newProject = new Project();
        newProject.setName(projectName);
        newProject.setDescription(projectDescription);

        return projectRepository.save(newProject);
    }

    @Override
    public Project getProjectById(Long id) {
        notNull(id, "project id can not be null");
        if (projectRepository.findById(id).isPresent()){
            return projectRepository.findById(id).get();
        }else throw new EntityNotFoundException();
    }

    @Override
    public Project update(ProjectDto projectDto) {
        notNull(projectDto, "projectDto can not be null");
        Long projectId = projectDto.getId();
        notNull(projectId, "id can not be empty");
        if (projectRepository.findById(projectId).isPresent()){
            String projectName = projectDto.getName();
            String projectDescription = projectDto.getDescription();
            notEmpty(projectName, "project name can not be empty");
            notEmpty(projectDescription, "project description can not be empty");
            Project project = projectRepository.findById(projectId).get();
            project.setName(projectName);
            project.setDescription(projectDescription);
            return projectRepository.save(project);
        }else  throw new EntityNotFoundException();
    }

    @Override
    public Project getProjectByName(String name) {
        notEmpty(name, "id can not be empty");
        notNull(name, "id can not be null");
        if (!projectRepository.existsByName(name)) throw new EntityNotFoundException();
        return projectRepository.findByName(name);
    }

    public List<Project> findAll() {
        List<Project> projects = projectRepository.findAll();
        notEmpty(projects, "There is no project created");
        return projects;
    }
}
