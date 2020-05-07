package com.ilegra.sweexpress.brunovieira.calculator.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperationsTest {

    @Test
    public void shouldSum() {
        assertEquals(20.0, new Sum().execute(10, 10), 0);
    }

    @Test
    public void shouldSubtract() {
        assertEquals(15.0, new Subtraction().execute(20, 5), 0);
    }

    @Test
    public void shouldMultiply() {
        assertEquals(100.0, new Multiplication().execute(10, 10), 0);
    }

    @Test
    public void shouldDivide() {
        assertEquals(5.0, new Division().execute(50, 10), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfDivideByZero() {
        new Division().execute(50, 0);
    }

    @Test
    public void shouldDoPower() {
        assertEquals(25.0, new Power().execute(5, 2), 0);
    }
}
