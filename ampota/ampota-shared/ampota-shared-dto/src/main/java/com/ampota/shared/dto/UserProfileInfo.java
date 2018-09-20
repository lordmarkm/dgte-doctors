package com.ampota.shared.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import xyz.quadx.shared.dto.BaseInfo;

public class UserProfileInfo extends BaseInfo {

    private String username;
    @NotNull private String contactNumber;
    private List<AddressInfo> addresses;
    private boolean shipping;
    private List<ShippingProviderInfo> shippingProviders;
    private boolean meetup;
    private List<MeetupInfo> meetups;
    private boolean ampotaCoin;
    private List<BankAccountInfo> bankAccounts;
    private boolean emailConfirmed;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public List<AddressInfo> getAddresses() {
        return addresses;
    }
    public void setAddresses(List<AddressInfo> addresses) {
        this.addresses = addresses;
    }
    public boolean isShipping() {
        return shipping;
    }
    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }
    public List<ShippingProviderInfo> getShippingProviders() {
        return shippingProviders;
    }
    public void setShippingProviders(List<ShippingProviderInfo> shippingProviders) {
        this.shippingProviders = shippingProviders;
    }
    public boolean isMeetup() {
        return meetup;
    }
    public void setMeetup(boolean meetup) {
        this.meetup = meetup;
    }
    public List<MeetupInfo> getMeetups() {
        return meetups;
    }
    public void setMeetups(List<MeetupInfo> meetups) {
        this.meetups = meetups;
    }
    public boolean isAmpotaCoin() {
        return ampotaCoin;
    }
    public void setAmpotaCoin(boolean ampotaCoin) {
        this.ampotaCoin = ampotaCoin;
    }
    public List<BankAccountInfo> getBankAccounts() {
        return bankAccounts;
    }
    public void setBankAccounts(List<BankAccountInfo> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }
    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

}
