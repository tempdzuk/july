package com.test.endpoint;

import com.test.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

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