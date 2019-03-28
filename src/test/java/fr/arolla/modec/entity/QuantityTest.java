package fr.arolla.modec.entity;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QuantityTest {
    @Test
    public void quantity_can_be_null() {
        Quantity quantity = new Quantity(0);
        assertThat(quantity.getQuantity()).isEqualTo(0);
    }

    @Test
    public void quantity_can_be_positive() {
        Quantity quantity = new Quantity(1);
        assertThat(quantity.getQuantity()).isEqualTo(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void quantity_cannot_be_negative() {
        new Quantity(-1);
    }
}
