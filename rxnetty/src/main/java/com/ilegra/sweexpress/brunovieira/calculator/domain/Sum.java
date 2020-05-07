package com.ilegra.sweexpress.brunovieira.calculator.domain;

public class Sum implements Function {
    @Override
    public double execute(double x, double y) {
        return x + y;
    }
}
