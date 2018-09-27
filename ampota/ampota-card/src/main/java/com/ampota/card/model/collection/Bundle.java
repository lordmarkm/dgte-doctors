package com.ampota.card.model.collection;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import com.ampota.card.model.Card;
import com.ampota.shared.dto.card.collection.CardCondition;
import com.ampota.shared.dto.card.collection.SellMode;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "bundle")
public class Bundle extends BaseEntity {

    @Column(name = "owner_username", nullable = false)
    private String owner;

    @ManyToOne(optional = true)
    @JoinColumn(name = "binder_id")
    private Binder binder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "card_qty")
    private int qty;

    /**
     * Use this for both piece and all, treat set price separately
     */
    @Column(name = "sell_price")
    private BigDecimal sellPrice;

    @Column(name = "sell_price_set")
    private BigDecimal sellPriceSet;

    @Column(name = "sell_mode")
    @Enumerated(EnumType.STRING)
    private SellMode sellMode;

    @Column(name = "bought_price")
    private BigDecimal boughtPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CardCondition condition;

    @Column(name = "for_sale")
    @Type(type = "yes_no")
    private boolean forSale;

    @ElementCollection
    @CollectionTable(name = "bundle_pics")
    private List<String> pictures;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Binder getBinder() {
        return binder;
    }

    public void setBinder(Binder binder) {
        this.binder = binder;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
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

}
