package com.ilegra.swe.brunovieira.toll.domain;

public class Truck extends Vehicle {

    private double rate = 3.95;
    private int axles;

    public Truck(int axles) {
        this.rate = rate * axles;
        this.axles = axles;
    }

    @Override
    public double getRate() {
        return rate;
    }

    public int getAxles() {
        return axles;
    }
}
