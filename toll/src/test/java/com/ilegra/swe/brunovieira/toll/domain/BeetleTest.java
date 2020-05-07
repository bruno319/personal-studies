package com.ilegra.swe.brunovieira.toll.domain;

import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BeetleTest {

    @Test
    public void shouldCreateBeetle() {
        Beetle beetle = new Beetle();
        assertEquals(2.11, beetle.getRate(), 0);
    }

    @Test
    public void shouldCalculateChange() throws InvalidPaymentException {
        Beetle beetle = new Beetle();
        assertEquals(0.89, beetle.changeFor(3.00), 0);
    }

    @Test(expected = InvalidPaymentException.class)
    public void shouldThrowExceptionIfPaymentIsNotEnough() throws InvalidPaymentException {
        Beetle beetle = new Beetle();
        beetle.changeFor(2.00);
    }

}
