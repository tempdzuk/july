package com.test.julyOld.endpoint.model.project;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProjectCreationDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

}
