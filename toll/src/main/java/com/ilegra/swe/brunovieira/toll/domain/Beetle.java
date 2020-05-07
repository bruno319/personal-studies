package com.ilegra.swe.brunovieira.toll.domain;

public class Beetle extends Vehicle {

    private final double rate = 2.11;

    @Override
    public double getRate() {
        return rate;
    }
}
