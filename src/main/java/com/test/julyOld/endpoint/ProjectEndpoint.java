package com.test.julyOld.endpoint;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.julyOld.endpoint.component.ProjectConverter;
import com.test.julyOld.endpoint.model.ProjectDto;
import com.test.julyOld.entity.Project;
import com.test.julyOld.service.impl.ProjectServiceImpl;
import com.test.julyOld.service.model.ProjectCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "project")
public class ProjectEndpoint {

    @Autowired
    private ProjectServiceImpl projectService;

    @Autowired
    private ProjectConverter projectConverter;

    @RequestMapping(method = RequestMethod.POST, value = "project/add")
    public ProjectDto create(@RequestBody final ProjectDto projectDto){
            final ProjectCreationRequest projectCreationRequest = projectConverter.convertDtoToRequest(projectDto);
            final Project createdProject = projectService.create(projectCreationRequest);
            return projectConverter.convertEntityToDto(createdProject);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "project/modify")
    public ProjectDto update(@RequestBody final ProjectDto projectDto){
        final Project updatedProject = projectService.update(projectDto);
        return projectConverter.convertEntityToDto(updatedProject);
    }

    @RequestMapping(method = RequestMethod.GET, value = "project/get/{name}")
    public ProjectDto getProjectByName(@PathVariable String name){
        final Project receivedProject = projectService.getProjectByName(name);
        return projectConverter.convertEntityToDto(receivedProject);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<ProjectDto> getAllProject(){
        final List<Project> receivedProjects = projectService.findAll();
        return projectConverter.convertEntityToDtoList(receivedProjects);
    }
}
