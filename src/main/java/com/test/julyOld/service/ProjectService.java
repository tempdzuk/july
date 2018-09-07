package com.test.julyOld.service;

import com.test.julyOld.endpoint.model.ProjectDto;
import com.test.julyOld.entity.Project;
import com.test.julyOld.service.model.ProjectCreationRequest;

import java.util.List;

public interface ProjectService {

    Project create(ProjectCreationRequest projectCreationRequest);

    Project getProjectById(Long id);

    Project update(ProjectDto projectDto);

    Project getProjectByName(String name);

    List<Project> findAll();
}
