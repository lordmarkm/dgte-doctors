package com.ampota.user.service;

import com.ampota.user.model.Bank;

import xyz.xpay.shared.jpa.service.XpayJpaService;

public interface BankService extends XpayJpaService<Bank>, BankServiceCustom {

}
