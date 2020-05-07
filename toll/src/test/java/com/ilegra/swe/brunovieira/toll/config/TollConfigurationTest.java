package com.ilegra.swe.brunovieira.toll.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.ilegra.swe.brunovieira.toll.controller.TollController;
import com.ilegra.swe.brunovieira.toll.domain.VehicleFactory;
import com.ilegra.swe.brunovieira.toll.service.TollService;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TollConfigurationTest {

    TollAppConfiguration tollAppConfiguration;

    @Before
    public void setup() {
        tollAppConfiguration = new TollAppConfiguration();
    }

    @Test
    public void shouldReturnSpringBus() {
        SpringBus cxf = tollAppConfiguration.cxf();
        assertNotNull(cxf);
    }

    @Test
    public void shouldReturnServer() {
        Server server = tollAppConfiguration.jaxRsServer();
        assertNotNull(server);
    }

    @Test
    public void shouldReturnJsonProvider() {
        JacksonJsonProvider jsonProvider = tollAppConfiguration.jsonProvider();
        assertNotNull(jsonProvider);
    }

    @Test
    public void shouldReturnTollController() {
        TollController tollController = tollAppConfiguration.tollController();
        assertNotNull(tollController);
    }

    @Test
    public void shouldReturnTollService() {
        TollService tollService = tollAppConfiguration.tollService();
        assertNotNull(tollService);
    }

    @Test
    public void shouldReturnVehicleFactory() {
        VehicleFactory vehicleFactory = tollAppConfiguration.vehicleFactory();
        assertNotNull(vehicleFactory);
    }

}
