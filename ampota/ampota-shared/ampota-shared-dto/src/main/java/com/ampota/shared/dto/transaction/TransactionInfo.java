package com.ampota.shared.dto.transaction;

import java.math.BigDecimal;
import java.util.List;

import com.ampota.shared.dto.AddressInfo;
import com.ampota.shared.dto.BankAccountInfo;
import com.ampota.shared.dto.MeetupInfo;
import com.ampota.shared.dto.UserProfileInfo;

import xyz.quadx.shared.dto.BaseInfo;

public class TransactionInfo extends BaseInfo {

    private long buyerId;
    private UserProfileInfo buyerProfile;
    private long sellerId;
    private UserProfileInfo sellerProfile;
    private BigDecimal total;
    private TransactionStatus status = TransactionStatus.NEW;
    private DeliveryMethod deliveryMethod;
    private Long meetupId;
    private MeetupInfo meetup;
    private AddressInfo shippingAddress;
    private PaymentMethod paymentMethod;
    private BankAccountInfo depositInfo;
    private String remarks;
    private List<OrderInfo> orders;
    private String frontendGeneratedTransactionNo;
    private String buyerName;
    private String buyerLink;
    private String sellerName;
    private String sellerLink;

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
    public UserProfileInfo getBuyerProfile() {
        return buyerProfile;
    }
    public void setBuyerProfile(UserProfileInfo buyerProfile) {
        this.buyerProfile = buyerProfile;
    }
    public UserProfileInfo getSellerProfile() {
        return sellerProfile;
    }
    public void setSellerProfile(UserProfileInfo sellerProfile) {
        this.sellerProfile = sellerProfile;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public String getFrontendGeneratedTransactionNo() {
        return frontendGeneratedTransactionNo;
    }
    public void setFrontendGeneratedTransactionNo(String frontendGeneratedTransactionNo) {
        this.frontendGeneratedTransactionNo = frontendGeneratedTransactionNo;
    }
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
    public Long getMeetupId() {
        return meetupId;
    }
    public void setMeetupId(Long meetupId) {
        this.meetupId = meetupId;
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
