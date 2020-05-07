package com.ilegra.swe.brunovieira.toll.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.ilegra.swe.brunovieira.toll.controller.TollController;
import com.ilegra.swe.brunovieira.toll.domain.VehicleFactory;
import com.ilegra.swe.brunovieira.toll.service.TollServiceImpl;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.ws.rs.ext.RuntimeDelegate;

@Configuration
public class TollAppConfiguration {

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer() {
        JAXRSServerFactoryBean factory =
                RuntimeDelegate.getInstance().createEndpoint(tollController(), JAXRSServerFactoryBean.class);
        factory.setServiceBean(tollController());
        factory.setProvider(jsonProvider());
        return factory.create();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

    @Bean
    public TollController tollController() {
        return new TollController(tollService());
    }

    @Bean
    public VehicleFactory vehicleFactory() {
        return new VehicleFactory();
    }

    @Bean
    public TollServiceImpl tollService() {
        return new TollServiceImpl(vehicleFactory());
    }
}
