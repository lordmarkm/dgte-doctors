package com.ampota.shared.dto.card.collection;

import java.math.BigDecimal;
import java.util.List;

import com.ampota.shared.dto.card.CardInfo;

import xyz.quadx.shared.dto.BaseInfo;

public class BundleInfo extends BaseInfo {

    private String owner;
    private String ownerName;
    private BinderInfo binder;
    private CardInfo card;
    private int qty;
    private BigDecimal sellPrice;
    private BigDecimal sellPriceSet;
    private BigDecimal boughtPrice;
    private CardCondition condition;
    private boolean forSale;
    private SellMode sellMode;
    private List<String> pictures;

    //alias to prevent css class collission with bootstrap 'card'
    public CardInfo getCardDetails() {
        return card;
    }
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
    public SellMode getSellMode() {
        return sellMode;
    }
    public void setSellMode(SellMode sellMode) {
        this.sellMode = sellMode;
    }
    public BigDecimal getSellPriceSet() {
        return sellPriceSet;
    }
    public void setSellPriceSet(BigDecimal sellPriceSet) {
        this.sellPriceSet = sellPriceSet;
    }
    public List<String> getPictures() {
        return pictures;
    }
    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

}
