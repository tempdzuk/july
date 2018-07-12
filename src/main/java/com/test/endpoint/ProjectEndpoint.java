package com.test.endpoint;

import com.test.endpoint.component.ProjectConverter;
import com.test.endpoint.model.ProjectDto;
import com.test.entity.Project;
import com.test.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectEndpoint {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectConverter projectConverter;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ProjectDto getAllProjects(final ProjectDto projectDto){

        final Project project = projectService.create(projectConverter.convert(projectDto));
        return projectConverter.convert(project);
    }
}
