package com.ampota.user.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "meetup")
public class Meetup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
