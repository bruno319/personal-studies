package com.ilegra.swe.brunovieira.appservice;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppServiceApplication {

    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixStreamServlet(){
        return new ServletRegistrationBean<>(new HystrixMetricsStreamServlet(), "/actuator/hystrix.stream");
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppServiceApplication.class, args);
        EurekaClient eurekaClient = context.getBean(EurekaClient.class);
        eurekaClient.getApplicationInfoManager().setInstanceStatus(InstanceInfo.InstanceStatus.UP);
    }
}
