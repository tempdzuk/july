package com.test.julyOld.service.model.taskRequests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskModificationRequest {

    private String id;

    private String story;

    private String description;

    private String projectId;

    private String userId;
}
