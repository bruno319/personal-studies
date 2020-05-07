package com.ilegra.swe.brunovieira.toll.domain;

import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TruckTest {

    @Test
    public void shouldCreateTruck() {
        Truck truck = new Truck(2);
        assertEquals(7.90, truck.getRate(), 0);
        assertEquals(2, truck.getAxles());
    }

    @Test
    public void shouldCalculateChange() throws InvalidPaymentException {
        Truck truck = new Truck(3);
        assertEquals(3.15, truck.changeFor(15.00), 0);
    }

    @Test(expected = InvalidPaymentException.class)
    public void shouldThrowExceptionIfPaymentIsNotEnough() throws InvalidPaymentException {
        Truck truck = new Truck(2);
        truck.changeFor(7.00);
    }
}
