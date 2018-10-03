package com.test.julyOld.endpoint.model.task;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskModificationDto {

    @NotNull
    private String id;

    private String story;

    private String description;

    private String projectId;

    private String userId;
}
