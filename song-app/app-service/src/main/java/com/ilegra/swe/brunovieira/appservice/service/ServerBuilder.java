package com.ilegra.swe.brunovieira.appservice.service;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ServerBuilder {

    private EurekaClient eurekaClient;
    private ServicesEnum service;

    @Autowired
    public ServerBuilder(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    public ServerBuilder service(ServicesEnum service) {
        this.service = service;
        return this;
    }

    public Optional<List<Server>> build() {
        if (service == null) return Optional.empty();

        Application application = eurekaClient.getApplication(service.getName());
        service = null;

        if (application == null) return Optional.empty();

        return Optional.of(application
                .getInstances()
                .stream()
                .map(instance -> new Server(instance.getHostName(), instance.getPort()))
                .collect(Collectors.toList()));
    }
}
