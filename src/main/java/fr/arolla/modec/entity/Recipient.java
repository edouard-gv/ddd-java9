package fr.arolla.modec.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Recipient {
    private String recipientFullName;
    private String email;

    public Recipient() { // for JPA
    }

    public Recipient(String recipientFullName, String email) {
        this.recipientFullName = recipientFullName;
        this.email = email;
    }

    public String getRecipientFullName() {
        return recipientFullName;
    }

    public String getEmail() {
        return email;
    }
}
