package com.ampota.user.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

/**
 *
 * @author mbmartinez on 18 Sep 2018
 *
 */
@Embeddable
public class BankAccount {

    @Type(type = "yes_no")
    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "accountNumber")
    private String accountNumber;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
