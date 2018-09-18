package com.ampota.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "bank")
public class Bank extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
