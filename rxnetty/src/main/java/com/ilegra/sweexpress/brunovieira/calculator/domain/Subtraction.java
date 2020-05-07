package com.ilegra.sweexpress.brunovieira.calculator.domain;

public class Subtraction implements Function {
    @Override
    public double execute(double x, double y) {
        return x - y;
    }
}
