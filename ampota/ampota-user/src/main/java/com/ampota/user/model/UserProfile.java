package com.ampota.user.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "user_profile")
public class UserProfile extends BaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "contact_number")
    private String contactNumber;

    @ElementCollection
    @CollectionTable(name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<Address> addresses;

    @Column(name = "ship")
    @Type(type = "yes_no")
    private boolean shipping;

    @ManyToMany
    @JoinTable(name = "user_x_shipping_provider")
    private List<ShippingProvider> shippingProviders;

    @Column(name = "meetup")
    @Type(type = "yes_no")
    private boolean meetup;

    @ManyToMany
    @JoinTable(name = "user_x_meetup")
    private List<Meetup> meetups;

    @Column(name = "amp_coin")
    @Type(type = "yes_no")
    private boolean ampotaCoin;

    @ElementCollection
    @CollectionTable(name = "bank_account")
    private List<BankAccount> bankAccounts;

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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean isShipping() {
        return shipping;
    }

    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }

    public List<ShippingProvider> getShippingProviders() {
        return shippingProviders;
    }

    public void setShippingProviders(List<ShippingProvider> shippingProviders) {
        this.shippingProviders = shippingProviders;
    }

    public boolean isMeetup() {
        return meetup;
    }

    public void setMeetup(boolean meetup) {
        this.meetup = meetup;
    }

    public List<Meetup> getMeetups() {
        return meetups;
    }

    public void setMeetups(List<Meetup> meetups) {
        this.meetups = meetups;
    }

    public boolean isAmpotaCoin() {
        return ampotaCoin;
    }

    public void setAmpotaCoin(boolean ampotaCoin) {
        this.ampotaCoin = ampotaCoin;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

}
