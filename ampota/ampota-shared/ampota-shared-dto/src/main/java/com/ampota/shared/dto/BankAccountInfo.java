package com.ampota.shared.dto;

public class BankAccountInfo {

    private BankInfo bank;
    private String accountName;
    private String accountNumber;

    public BankInfo getBank() {
        return bank;
    }
    public void setBank(BankInfo bank) {
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
