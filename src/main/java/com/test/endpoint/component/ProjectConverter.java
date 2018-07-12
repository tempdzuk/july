package com.test.endpoint.component;


import com.test.endpoint.model.ProjectDto;
import com.test.entity.Project;
import com.test.service.model.ProjectCreationRequest;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {


    public ProjectCreationRequest convert(final ProjectDto projectDto){
        return new ProjectCreationRequest();
    }

    public ProjectDto convert(final Project project){
        return null;
    }
}
