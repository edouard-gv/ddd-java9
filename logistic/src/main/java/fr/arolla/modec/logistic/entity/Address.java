package fr.arolla.modec.logistic.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Address implements Serializable {

    private String recipientName;
    private String line1;
    private String city;
    private String zipCode;
    private String isoCountryCode;

    public Address(String recipientName, String line1, String city, String zipCode, String isoCountryCode) {
        this.recipientName = recipientName;
        this.line1 = line1;
        this.city = city;
        this.zipCode = zipCode;
        this.isoCountryCode = isoCountryCode;
    }

    public Address() {//for JPA
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getLine1() {
        return line1;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address that = (Address) o;
        return Objects.equals(recipientName, that.recipientName) &&
                Objects.equals(line1, that.line1) &&
                Objects.equals(city, that.city) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(isoCountryCode, that.isoCountryCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(recipientName, line1, city, zipCode, isoCountryCode);
    }
}
