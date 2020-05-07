package com.ilegra.swe.brunovieira.appservice.service;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoadBalancerManagerTest {

    @Mock
    private ServerBuilder serverBuilder;

    private LoadBalancerManager loadBalancerManager;

    @Before
    public void setup() {
        loadBalancerManager = new LoadBalancerManager(serverBuilder);
        when(serverBuilder.service(any())).thenReturn(serverBuilder);
    }

    @Test
    public void shouldCreateLoadBalancerForFirstServiceInServicesEnum() {
        when(serverBuilder.build()).thenReturn(Optional.of(Collections.singletonList(new Server(""))));
        loadBalancerManager.discoverServices();
        Optional<ILoadBalancer> loadBalancer = loadBalancerManager.getLoadBalancer(ServicesEnum.values()[0]);

        assertTrue(loadBalancer.isPresent());
    }

    @Test
    public void shouldReturnEmptyOptionalIfServiceIsUnavailable() {
        loadBalancerManager.discoverServices();
        Optional<ILoadBalancer> loadBalancer = loadBalancerManager.getLoadBalancer(ServicesEnum.values()[0]);

        assertFalse(loadBalancer.isPresent());
    }
}
