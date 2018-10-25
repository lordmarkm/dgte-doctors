package com.ampota.card.model.transaction;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Type;

import com.ampota.shared.dto.AddressInfo;
import com.ampota.shared.dto.BankAccountInfo;
import com.ampota.shared.dto.MeetupInfo;
import com.ampota.shared.dto.transaction.DeliveryMethod;
import com.ampota.shared.dto.transaction.PaymentMethod;
import com.ampota.shared.dto.transaction.TransactionStatus;

import xyz.xpay.shared.jpa.model.Address;
import xyz.xpay.shared.jpa.model.BaseEntity;

@SuppressWarnings("unused")
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
    private BigDecimal total;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.NEW;

    @Column(name = "meetup_id")
    private long meetupId;

    @Embedded
    private Address shippingAddress;

    @Column(name = "delivery_method")
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Embedded
    private BankAccount depositInfo;

    @Column(name = "remarks")
    @Type(type = "text")
    private String remarks;

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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
