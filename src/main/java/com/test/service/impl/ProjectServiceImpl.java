package com.test.service.impl;

import com.test.endpoint.model.ProjectDto;
import com.test.entity.Project;
import com.test.service.ProjectService;
import com.test.service.model.ProjectCreationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    public Project create(final ProjectCreationRequest projectCreationRequest) {
        return null;
    }

    public List<Project> findAll() {
        return null;
    }
}
