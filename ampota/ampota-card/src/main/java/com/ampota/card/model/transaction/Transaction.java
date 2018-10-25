package com.ampota.card.model.transaction;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ampota.shared.dto.transaction.TransactionStatus;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "txn")
public class Transaction extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "buyer_id", nullable = false)
    private long buyerId;

    @Column(name = "seller_id", nullable = false)
    private long sellerId;

    @ElementCollection
    @CollectionTable(name = "txn_order")
    private List<Order> orders;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.NEW;

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

}
