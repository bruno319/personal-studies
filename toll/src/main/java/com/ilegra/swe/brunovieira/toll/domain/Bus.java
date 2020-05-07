package com.ilegra.swe.brunovieira.toll.domain;

public class Bus extends Vehicle {

    private final double rate = 1.59;

    @Override
    public double getRate() {
        return rate;
    }
}
