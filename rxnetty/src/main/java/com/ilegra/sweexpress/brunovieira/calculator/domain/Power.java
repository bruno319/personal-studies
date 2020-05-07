package com.ilegra.sweexpress.brunovieira.calculator.domain;

public class Power implements Function {
    @Override
    public double execute(double x, double y) {
        return Math.pow(x, y);
    }
}
