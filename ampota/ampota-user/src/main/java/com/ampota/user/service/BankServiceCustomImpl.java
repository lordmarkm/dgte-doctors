package com.ampota.user.service;

import com.ampota.shared.dto.BankInfo;
import com.ampota.user.model.Bank;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class BankServiceCustomImpl extends XpayJpaServiceCustomImpl<Bank, BankInfo, BankService>
    implements BankServiceCustom {

}
