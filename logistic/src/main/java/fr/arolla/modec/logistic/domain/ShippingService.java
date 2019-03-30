package fr.arolla.modec.logistic.domain;

public class ShippingService {
    private Long id;

    private String code;

    private String carrier;

    private String level;

    public ShippingService(String code, String carrier, String level) {
        this.code = code;
        this.carrier = carrier;
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getLevel() {
        return level;
    }
}
