package com.ampota.user.service;

import com.ampota.shared.dto.BankInfo;
import com.ampota.user.model.Bank;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;

public interface BankServiceCustom extends XpayJpaServiceCustom<Bank, BankInfo> {

}
