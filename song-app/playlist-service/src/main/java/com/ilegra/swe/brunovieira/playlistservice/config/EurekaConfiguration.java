package com.ilegra.swe.brunovieira.playlistservice.config;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.EurekaClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

@Configuration
public class EurekaConfiguration {

    @Bean
    public EurekaClient eurekaClient() {
        return new DiscoveryClient(applicationInfoManager(), defaultEurekaClientConfig());
    }

    @Bean
    public ApplicationListener discoveryClientShutdownListener() {
        return applicationEvent -> {
            if (applicationEvent instanceof ContextClosedEvent) {
                DiscoveryManager.getInstance()
                        .getEurekaClient()
                        .shutdown();
            }
        };
    }

    @Bean
    public CustomDataCenterInstanceConfig customDataCenterInstanceConfig() {
        return new CustomDataCenterInstanceConfig();
    }

    @Bean
    public EurekaConfigBasedInstanceInfoProvider eurekaConfigBasedInstanceInfoProvider() {
        return new EurekaConfigBasedInstanceInfoProvider(customDataCenterInstanceConfig());
    }

    @Bean
    public ApplicationInfoManager applicationInfoManager() {
        return new ApplicationInfoManager(
                customDataCenterInstanceConfig(),
                eurekaConfigBasedInstanceInfoProvider().get());
    }

    @Bean
    public DefaultEurekaClientConfig defaultEurekaClientConfig() {
        return new DefaultEurekaClientConfig();
    }
}
