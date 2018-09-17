package com.test.julyOld.service;

import com.test.julyOld.endpoint.model.ProjectDto;
import com.test.julyOld.entity.Project;
import com.test.julyOld.service.model.ProjectCreationRequest;

import java.util.List;

public interface ProjectService {
    Project create(ProjectCreationRequest projectCreationRequest);

    Project getById(Long id);

//    Project getByIdWithTasks(Long id);

    Project update(ProjectDto projectDto);



    // misht id ov vercru
//    Project getProjectByName(String name);

    List<Project> findAll();
}
