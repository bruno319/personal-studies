package com.ilegra.sweexpress.brunovieira.calculator.domain;

public class Multiplication implements Function {
    @Override
    public double execute(double x, double y) {
        return x * y;
    }
}
