package com.ampota.card.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.card.service.transaction.TransactionService;
import com.ampota.shared.dto.transaction.TransactionInfo;

import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/transaction")
public class TransactionResource extends BaseResource<TransactionInfo, TransactionService> {

}
