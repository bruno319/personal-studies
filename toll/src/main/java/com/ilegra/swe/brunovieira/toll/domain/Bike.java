package com.ilegra.swe.brunovieira.toll.domain;

public class Bike extends Vehicle {

    private final double rate = 0.49;

    @Override
    public double getRate() {
        return rate;
    }
}
