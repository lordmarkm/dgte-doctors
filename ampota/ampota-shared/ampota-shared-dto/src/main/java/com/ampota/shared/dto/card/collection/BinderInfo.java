package com.ampota.shared.dto.card.collection;

import java.math.BigDecimal;

import xyz.quadx.shared.dto.BaseInfo;

public class BinderInfo extends BaseInfo {

    private String owner;
    private String name;
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
