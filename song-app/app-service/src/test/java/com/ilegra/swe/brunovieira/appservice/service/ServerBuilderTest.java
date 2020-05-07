package com.ilegra.swe.brunovieira.appservice.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.loadbalancer.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServerBuilderTest {

    @Mock
    private EurekaClient eurekaClient;

    @Mock
    private Application application;

    @Mock
    private InstanceInfo instanceInfo;

    private ServerBuilder serverBuilder;

    @Before
    public void setup() {
        serverBuilder = new ServerBuilder(eurekaClient);
    }

    @Test
    public void shouldCreateServersFromInstances() {
        when(eurekaClient.getApplication(eq(ServicesEnum.SONG_SERVICE.getName()))).thenReturn(application);
        when(application.getInstances()).thenReturn(Collections.singletonList(instanceInfo));
        when(instanceInfo.getHostName()).thenReturn("localhost");
        when(instanceInfo.getPort()).thenReturn(8080);

        Optional<List<Server>> servers = serverBuilder.service(ServicesEnum.SONG_SERVICE).build();

        assertTrue(servers.isPresent());
        assertEquals(1, servers.get().size());
        assertEquals("localhost:8080", servers.get().get(0).getHostPort());
    }

    @Test
    public void shouldReturnEmptyOptionalIfServiceIsNotProvided() {
        Optional<List<Server>> servers = serverBuilder.build();

        assertFalse(servers.isPresent());
    }

    @Test
    public void shouldReturnEmptyOptionalIfEurekaDoesNotFindService() {
        Optional<List<Server>> servers = serverBuilder.service(ServicesEnum.SONG_SERVICE).build();

        assertFalse(servers.isPresent());
    }

}
