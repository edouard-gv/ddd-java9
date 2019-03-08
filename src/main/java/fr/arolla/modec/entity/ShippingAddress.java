package fr.arolla.modec.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShippingAddress implements Serializable {

    private String fullName;
    private String line1;
    private String city;
    private String zipCode;
    private String isoCountryCode;

    public ShippingAddress(String fullName, String line1, String city, String zipCode, String isoCountryCode) {
        this.fullName = fullName;
        this.line1 = line1;
        this.city = city;
        this.zipCode = zipCode;
        this.isoCountryCode = isoCountryCode;
    }

    public ShippingAddress() {//for JPA
    }

    public String getFullName() {
        return fullName;
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
        if (!(o instanceof ShippingAddress)) return false;
        ShippingAddress that = (ShippingAddress) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(line1, that.line1) &&
                Objects.equals(city, that.city) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(isoCountryCode, that.isoCountryCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fullName, line1, city, zipCode, isoCountryCode);
    }
}
