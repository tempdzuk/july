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

        final String name = projectCreationRequest.getName();
        final String description = projectCreationRequest.getDescription();
        validate(name, description);

        final Project newProject = new Project();
        newProject.setName(name);
        newProject.setDescription(description);

        return projectRepository.save(newProject);
    }

    @Override
    public Project getById(Long id) {
        notNull(id, "project id can not be null");
        final Project project = projectRepository.findById(id).orElse(null);
        if (project == null) throw new EntityNotFoundException();
        return project;
    }

    @Override
    public Project update(ProjectDto projectDto) {
        notNull(projectDto, "projectDto can not be null");
        final Long id = projectDto.getId();
        notNull(id, "id can not be empty");

        final Project existingProject = getById(id);
        final String name = projectDto.getName();
        final String description = projectDto.getDescription();
        validate(name, description);

        existingProject.setName(name);
        existingProject.setDescription(description);
        return projectRepository.save(existingProject);
    }


    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    private void validate(String name, String description) {
        notEmpty(name, "project name can not be empty");
        notEmpty(description, "project description can not be empty");
    }
}
