package com.ampota.card.model.transaction;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mbmartinez on 18 Sep 2018
 *
 */
@Embeddable
public class BankAccount {

    @Column(name = "bank_name")
    private String bank;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "accountNumber")
    private String accountNumber;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
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
