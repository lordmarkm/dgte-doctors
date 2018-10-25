package com.ampota.card.service.transaction;

import com.ampota.card.model.transaction.Transaction;
import com.ampota.shared.dto.transaction.TransactionInfo;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;

public interface TransactionServiceCustom extends XpayJpaServiceCustom<Transaction, TransactionInfo> {

}
