package fr.arolla.modec.entity;

import javax.persistence.*;

@Entity
public class ShippingService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String carrier;

    private String level;

    public ShippingService() { //for JPA
    }

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
