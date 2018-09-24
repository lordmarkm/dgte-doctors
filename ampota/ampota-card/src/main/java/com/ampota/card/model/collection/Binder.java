package com.ampota.card.model.collection;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "binder")
public class Binder extends BaseEntity {

    @Column(name = "owner_username", nullable = false)
    private String owner;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "bulk_purchase_amt")
    private BigDecimal bulkPurchaseAmt;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBulkPurchaseAmt() {
        return bulkPurchaseAmt;
    }

    public void setBulkPurchaseAmt(BigDecimal bulkPurchaseAmt) {
        this.bulkPurchaseAmt = bulkPurchaseAmt;
    }

}
