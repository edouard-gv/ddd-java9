package fr.arolla.modec.logistic.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
    private String email;

    public Contact() { // for JPA
    }

    public Contact(String email) {
        this.email = email;
    }
}
