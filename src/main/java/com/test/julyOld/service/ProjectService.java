package com.test.julyOld.service;

import com.test.julyOld.entity.Project;
import com.test.julyOld.service.model.projectRequsets.ProjectCreationRequest;
import com.test.julyOld.service.model.projectRequsets.ProjectModificationRequest;
import java.util.List;

public interface ProjectService {
    Project create(ProjectCreationRequest projectCreationRequest);

    Project get(String id);

    Project update(ProjectModificationRequest projectModificationRequest);

    List<Project> findAll();
}
