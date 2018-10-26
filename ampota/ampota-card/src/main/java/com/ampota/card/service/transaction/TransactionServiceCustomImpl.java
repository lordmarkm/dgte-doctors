package com.ampota.card.service.transaction;

import org.springframework.beans.factory.annotation.Autowired;

import com.ampota.card.model.transaction.Transaction;
import com.ampota.shared.client.MeetupClient;
import com.ampota.shared.client.UserProfileClient;
import com.ampota.shared.dto.UserProfileInfo;
import com.ampota.shared.dto.transaction.TransactionInfo;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class TransactionServiceCustomImpl extends XpayJpaServiceCustomImpl<Transaction, TransactionInfo, TransactionService>
    implements TransactionServiceCustom {

    @Autowired
    private UserProfileClient userProfileClient;

    @Autowired
    private MeetupClient meetupClient;

    @Override
    public TransactionInfo findOneInfo(Long id) {
        TransactionInfo txn = super.findOneInfo(id);
        txn.setSellerProfile(userProfileClient.findOneInfo(txn.getSellerId()).getBody());
        txn.setBuyerProfile(userProfileClient.findOneInfo(txn.getBuyerId()).getBody());
        if (null != txn.getMeetupId()) {
            txn.setMeetup(meetupClient.findOneInfo(txn.getMeetupId()).getBody());
        }
        return txn;
    }

    @Override
    public TransactionInfo saveInfo(TransactionInfo txn) {
        if (null == txn.getId() || txn.getId() == 0L) {
            UserProfileInfo currentUser = userProfileClient.getCurrentUser().getBody();
            txn.setBuyerProfile(currentUser);
        }
        return super.saveInfo(txn);
    }
}
