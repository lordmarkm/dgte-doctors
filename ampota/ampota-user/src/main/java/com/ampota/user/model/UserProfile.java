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

    @Column(name = "display_name")
    private String displayName;

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

    @Column(name = "shipping_note")
    private String shippingNote;

    @Column(name = "meetup")
    @Type(type = "yes_no")
    private boolean meetup;

    @ManyToMany
    @JoinTable(name = "user_x_meetup")
    private List<Meetup> meetups;

    @Column(name = "meetup_note")
    private String meetupNote;

    @Column(name = "cod")
    @Type(type = "yes_no")
    private boolean cod;

    @Column(name = "bank_dep")
    @Type(type = "yes_no")
    private boolean bankDeposit;

    @Column(name = "amp_coin")
    @Type(type = "yes_no")
    private boolean ampotaCoin;

    @ElementCollection
    @CollectionTable(name = "bank_account")
    private List<BankAccount> bankAccounts;

    @Column(name = "email_confirmed")
    @Type(type = "yes_no")
    private boolean emailConfirmed = false;

    @Column(name = "photo_url")
    @Type(type = "text")
    private String photoUrl;

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

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isCod() {
        return cod;
    }

    public void setCod(boolean cod) {
        this.cod = cod;
    }

    public boolean isBankDeposit() {
        return bankDeposit;
    }

    public void setBankDeposit(boolean bankDeposit) {
        this.bankDeposit = bankDeposit;
    }

    public String getShippingNote() {
        return shippingNote;
    }

    public void setShippingNote(String shippingNote) {
        this.shippingNote = shippingNote;
    }

    public String getMeetupNote() {
        return meetupNote;
    }

    public void setMeetupNote(String meetupNote) {
        this.meetupNote = meetupNote;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
