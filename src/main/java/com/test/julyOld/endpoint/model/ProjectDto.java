package com.test.julyOld.endpoint.model;


import javax.validation.constraints.NotNull;

public class ProjectDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

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


    @Override
    public String toString() {
        return "DTO: name-" + this.name + " description-" + this.description;
    }

}
