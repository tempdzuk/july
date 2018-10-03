package com.test.julyOld.endpoint.model.project;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProjectModificationDto {

    @NotNull
    private String id;

    private String name;

    private String description;

}
