package com.ilegra.swe.brunovieira.appservice.service;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@EnableScheduling
public class LoadBalancerManager {

    private ServerBuilder serverBuilder;
    private Map<String, Optional<ILoadBalancer>> loadBalancers;

    @Autowired
    public LoadBalancerManager(ServerBuilder serverBuilder) {
        this.serverBuilder = serverBuilder;
        this.loadBalancers = new HashMap<>();
    }

    @Scheduled(fixedDelay = 5000)
    public void discoverServices() {
        Stream.of(ServicesEnum.values()).forEach(service -> {
            Optional<List<Server>> servers = serverBuilder.service(service).build();

            if (!servers.isPresent()) {
                loadBalancers.put(service.getName(), Optional.empty());
                return;
            }
            BaseLoadBalancer baseLoadBalancer = LoadBalancerBuilder.newBuilder()
                    .buildFixedServerListLoadBalancer(servers.get());
            loadBalancers.put(service.getName(), Optional.of(baseLoadBalancer));
        });
    }

    public Optional<ILoadBalancer> getLoadBalancer(ServicesEnum service) {
        return loadBalancers.get(service.getName());
    }
}
