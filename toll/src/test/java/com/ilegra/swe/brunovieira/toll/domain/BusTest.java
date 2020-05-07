package com.ilegra.swe.brunovieira.toll.domain;

import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BusTest {

    @Test
    public void shouldCreateBus() {
        Bus bus = new Bus();
        assertEquals(1.59, bus.getRate(), 0);
    }

    @Test
    public void shouldCalculateChange() throws InvalidPaymentException {
        Bus bus = new Bus();
        assertEquals(0.41, bus.changeFor(2.00), 0);
    }

    @Test(expected = InvalidPaymentException.class)
    public void shouldThrowExceptionIfPaymentIsNotEnough() throws InvalidPaymentException {
        Bus bus = new Bus();
        bus.changeFor(1.00);
    }
}
