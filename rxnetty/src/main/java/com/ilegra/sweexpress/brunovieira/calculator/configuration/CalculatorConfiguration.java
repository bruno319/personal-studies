package com.ilegra.sweexpress.brunovieira.calculator.configuration;

import com.ilegra.sweexpress.brunovieira.calculator.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CalculatorConfiguration {

    @Bean
    public Sum sum() {
        return new Sum();
    }

    @Bean
    public Subtraction subtraction() {
        return new Subtraction();
    }

    @Bean
    public Multiplication multiplication() {
        return new Multiplication();
    }

    @Bean
    public Division division() {
        return new Division();
    }

    @Bean
    public Power power() {
        return new Power();
    }

    @Bean
    @Scope("singleton")
    public History history() {
        return new History();
    }
}
