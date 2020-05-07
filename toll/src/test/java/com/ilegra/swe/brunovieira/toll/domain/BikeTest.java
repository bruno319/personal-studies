package com.ilegra.swe.brunovieira.toll.domain;

import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BikeTest {

    @Test
    public void shouldCreateBike() {
        Bike bike = new Bike();
        assertEquals(0.49, bike.getRate(), 0);
    }

    @Test
    public void shouldCalculateChange() throws InvalidPaymentException {
        Bike bike = new Bike();
        assertEquals(0.51, bike.changeFor(1.00), 0);
    }

    @Test(expected = InvalidPaymentException.class)
    public void shouldThrowExceptionIfPaymentIsNotEnough() throws InvalidPaymentException {
        Bike bike = new Bike();
        bike.changeFor(0.10);
    }
}
