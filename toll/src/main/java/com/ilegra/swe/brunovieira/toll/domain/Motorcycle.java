package com.ilegra.swe.brunovieira.toll.domain;

public class Motorcycle extends Vehicle {

    private final double rate = 1.00;

    @Override
    public double getRate() {
        return rate;
    }

}
