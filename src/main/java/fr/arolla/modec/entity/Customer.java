package fr.arolla.modec.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Customer {
    private String fullName;
    private String email;

    public Customer() { // for JPA
    }

    public Customer(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }
}
