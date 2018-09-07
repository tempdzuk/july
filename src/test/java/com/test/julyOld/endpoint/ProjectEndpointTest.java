package com.test.julyOld.endpoint;

import com.test.julyOld.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProjectEndpointTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectEndpoint projectEndpoint;

    @Test
    public void testCreation(){

    }

}