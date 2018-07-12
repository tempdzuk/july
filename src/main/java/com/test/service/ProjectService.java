package com.test.service;

import com.test.endpoint.model.ProjectDto;
import com.test.entity.Project;
import com.test.service.model.ProjectCreationRequest;

import java.util.List;

public interface ProjectService {

    Project create(ProjectCreationRequest projectCreationRequest);

    List<Project> findAll();
}
