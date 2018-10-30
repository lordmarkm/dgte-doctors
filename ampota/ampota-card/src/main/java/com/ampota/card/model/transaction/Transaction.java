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

import com.ampota.shared.dto.transaction.DeliveryMethod;
import com.ampota.shared.dto.transaction.PaymentMethod;
import com.ampota.shared.dto.transaction.TransactionStatus;

import xyz.xpay.shared.jpa.model.Address;
import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "txn")
public class Transaction extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Catch double submit
     */
    @Column(name = "fe_txn_no", unique = true)
    private String frontendGeneratedTransactionNo;

    @Column(name = "buyer_id", nullable = false)
    private long buyerId;

    @Column(name = "seller_id", nullable = false)
    private long sellerId;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_link")
    @Type(type = "text")
    private String buyerLink;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_link")
    @Type(type = "text")
    private String sellerLink;

    @ElementCollection
    @CollectionTable(name = "txn_order")
    private List<Order> orders;

    @Column(name = "total_price")
    private BigDecimal total;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.NEW;

    @Column(name = "meetup_id")
    private Long meetupId;

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

    public Long getMeetupId() {
        return meetupId;
    }

    public void setMeetupId(Long meetupId) {
        this.meetupId = meetupId;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BankAccount getDepositInfo() {
        return depositInfo;
    }

    public void setDepositInfo(BankAccount depositInfo) {
        this.depositInfo = depositInfo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFrontendGeneratedTransactionNo() {
        return frontendGeneratedTransactionNo;
    }

    public void setFrontendGeneratedTransactionNo(String frontendGeneratedTransactionNo) {
        this.frontendGeneratedTransactionNo = frontendGeneratedTransactionNo;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerLink() {
        return buyerLink;
    }

    public void setBuyerLink(String buyerLink) {
        this.buyerLink = buyerLink;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerLink() {
        return sellerLink;
    }

    public void setSellerLink(String sellerLink) {
        this.sellerLink = sellerLink;
    }

}
