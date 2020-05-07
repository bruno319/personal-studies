package com.ilegra.swe.brunovieira.toll.domain;

import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MotorcycleTest {

    @Test
    public void shouldCreateMotorcycle() {
        Motorcycle motorcycle = new Motorcycle();
        assertEquals(1.00, motorcycle.getRate(), 0);
    }

    @Test
    public void shouldCalculateChange() throws InvalidPaymentException {
        Motorcycle motorcycle = new Motorcycle();
        assertEquals(0.00, motorcycle.changeFor(1.00), 0);
    }

    @Test(expected = InvalidPaymentException.class)
    public void shouldThrowExceptionIfPaymentIsNotEnough() throws InvalidPaymentException {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.changeFor(0.99);
    }
}
