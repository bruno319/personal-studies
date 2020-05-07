package com.ilegra.sweexpress.brunovieira.calculator.domain;

public class Division implements Function {
    @Override
    public double execute(double x, double y) {
        if (y == 0) throw new IllegalArgumentException("Can't divide by zero");
        return x / y;
    }
}
