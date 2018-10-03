package com.test.julyOld.endpoint.model.project;

import com.test.julyOld.endpoint.model.task.TaskResponseDto;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class ProjectResponseDto {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private Set<TaskResponseDto> tasks;

}
