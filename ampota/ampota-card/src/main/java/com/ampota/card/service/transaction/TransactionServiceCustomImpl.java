package com.ampota.card.service.transaction;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.ampota.card.model.transaction.Transaction;
import com.ampota.card.service.collection.BundleService;
import com.ampota.shared.client.MeetupClient;
import com.ampota.shared.client.UserProfileClient;
import com.ampota.shared.dto.UserProfileInfo;
import com.ampota.shared.dto.transaction.OrderInfo;
import com.ampota.shared.dto.transaction.TransactionInfo;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;
import xyz.xpay.shared.util.BigDecimalUtil;

public class TransactionServiceCustomImpl extends XpayJpaServiceCustomImpl<Transaction, TransactionInfo, TransactionService>
    implements TransactionServiceCustom {

    @Autowired
    private UserProfileClient userProfileClient;

    @Autowired
    private MeetupClient meetupClient;

    @Autowired
    private BundleService bundleService;

    @Override
    public TransactionInfo findOneInfo(Long id) {
        TransactionInfo txn = super.findOneInfo(id);
        txn.setSellerProfile(userProfileClient.findOneInfo(txn.getSellerId()).getBody());
        txn.setBuyerProfile(userProfileClient.findOneInfo(txn.getBuyerId()).getBody());
        if (null != txn.getMeetupId()) {
            txn.setMeetup(meetupClient.findOneInfo(txn.getMeetupId()).getBody());
        }
        txn.getOrders().stream().forEach(o -> o.setBundle(bundleService.findOneInfo(o.getBundleId())));
        return txn;
    }

    @Override
    public TransactionInfo saveInfo(TransactionInfo txn) {
        if (null == txn.getId() || txn.getId() == 0L) {
            UserProfileInfo currentUser = userProfileClient.getCurrentUser().getBody();
            txn.setBuyerProfile(currentUser);
        }

        //compute total
        txn.setTotal(txn.getOrders().stream().map(OrderInfo::getPrice).reduce(BigDecimal.ZERO, BigDecimalUtil::add));

        return super.saveInfo(txn);
    }
}
