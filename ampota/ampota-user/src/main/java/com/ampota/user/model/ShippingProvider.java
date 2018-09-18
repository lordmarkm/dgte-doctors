package com.ampota.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "shipping_provider")
public class ShippingProvider extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
