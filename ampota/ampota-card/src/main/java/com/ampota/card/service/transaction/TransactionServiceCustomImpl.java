package com.ampota.card.service.transaction;

import com.ampota.card.model.transaction.Transaction;
import com.ampota.shared.dto.transaction.TransactionInfo;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class TransactionServiceCustomImpl extends XpayJpaServiceCustomImpl<Transaction, TransactionInfo, TransactionService>
    implements TransactionServiceCustom {

}
