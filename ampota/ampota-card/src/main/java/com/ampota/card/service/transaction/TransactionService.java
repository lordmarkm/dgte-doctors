package com.ampota.card.service.transaction;

import com.ampota.card.model.transaction.Transaction;

import xyz.xpay.shared.jpa.service.XpayJpaService;

public interface TransactionService extends XpayJpaService<Transaction>, TransactionServiceCustom {

}
