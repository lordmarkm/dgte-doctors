package com.ampota.shared.dto.transaction;

import java.math.BigDecimal;
import java.util.List;

import com.ampota.shared.dto.AddressInfo;
import com.ampota.shared.dto.BankAccountInfo;
import com.ampota.shared.dto.MeetupInfo;
import com.ampota.shared.dto.UserProfileInfo;

import xyz.quadx.shared.dto.BaseInfo;

public class TransactionInfo extends BaseInfo {

    private UserProfileInfo buyer;
    private UserProfileInfo seller;
    private BigDecimal totalPrice;
    private TransactionStatus status = TransactionStatus.NEW;
    private DeliveryMethod deliveryMethod;
    private MeetupInfo meetup;
    private AddressInfo shippingAddress;
    private PaymentMethod paymentMethod;
    private BankAccountInfo depositInfo;
    private String remarks;
    private List<OrderInfo> orders;

    public UserProfileInfo getBuyer() {
        return buyer;
    }
    public void setBuyer(UserProfileInfo buyer) {
        this.buyer = buyer;
    }
    public UserProfileInfo getSeller() {
        return seller;
    }
    public void setSeller(UserProfileInfo seller) {
        this.seller = seller;
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
    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }
    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
    public MeetupInfo getMeetup() {
        return meetup;
    }
    public void setMeetup(MeetupInfo meetup) {
        this.meetup = meetup;
    }
    public AddressInfo getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(AddressInfo shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public BankAccountInfo getDepositInfo() {
        return depositInfo;
    }
    public void setDepositInfo(BankAccountInfo depositInfo) {
        this.depositInfo = depositInfo;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public List<OrderInfo> getOrders() {
        return orders;
    }
    public void setOrders(List<OrderInfo> orders) {
        this.orders = orders;
    }

}
