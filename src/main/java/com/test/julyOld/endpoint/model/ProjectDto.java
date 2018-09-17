package com.test.julyOld.endpoint.model;


import javax.validation.constraints.NotNull;
import java.util.Set;

public class ProjectDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private Set<TaskDto> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskDto> tasks) {
        this.tasks = tasks;
    }

}
