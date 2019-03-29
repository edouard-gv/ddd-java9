package fr.arolla.modec.sales.entity;

import org.junit.Test;


public class QuantityTest {
    @Test
    public void quantity_can_be_null() {
        Quantity quantity = new Quantity(0);
    }

    @Test
    public void quantity_can_be_positive() {
        Quantity quantity = new Quantity(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void quantity_cannot_be_negative() {
        Quantity quantity = new Quantity(-1);
    }
}
