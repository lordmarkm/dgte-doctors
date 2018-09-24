package com.ampota.shared.dto.card.collection;

import java.math.BigDecimal;

import com.ampota.shared.dto.card.CardInfo;

import xyz.quadx.shared.dto.BaseInfo;

public class BundleInfo extends BaseInfo {

    private String owner;
    private BinderInfo binder;
    private CardInfo card;
    private int qty;
    private BigDecimal sellPrice;
    private BigDecimal boughtPrice;
    private CardCondition condition;
    private boolean forSale;

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public BinderInfo getBinder() {
        return binder;
    }
    public void setBinder(BinderInfo binder) {
        this.binder = binder;
    }
    public CardInfo getCard() {
        return card;
    }
    public void setCard(CardInfo card) {
        this.card = card;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public BigDecimal getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }
    public BigDecimal getBoughtPrice() {
        return boughtPrice;
    }
    public void setBoughtPrice(BigDecimal boughtPrice) {
        this.boughtPrice = boughtPrice;
    }
    public CardCondition getCondition() {
        return condition;
    }
    public void setCondition(CardCondition condition) {
        this.condition = condition;
    }
    public boolean isForSale() {
        return forSale;
    }
    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

}
