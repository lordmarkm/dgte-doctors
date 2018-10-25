package com.ampota.shared.dto.transaction;

import java.math.BigDecimal;

import com.ampota.shared.dto.card.collection.BundleInfo;

public class OrderInfo {

    private BundleInfo bundle;
    private int qty;
    private BigDecimal price;

    public BundleInfo getBundle() {
        return bundle;
    }
    public void setBundle(BundleInfo bundle) {
        this.bundle = bundle;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
