package com.ilegra.swe.brunovieira.toll.domain;

import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Vehicle {

    public abstract double getRate();

    public double changeFor(double payment) throws InvalidPaymentException {
        double change = payment - getRate();
        if (change < 0) throw new InvalidPaymentException("Money is not enough");
        return new BigDecimal(change).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }
}
