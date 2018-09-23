package com.ampota.card.service;

import com.ampota.card.model.Card;
import com.ampota.shared.dto.card.CardInfo;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;

public interface CardServiceCustom extends XpayJpaServiceCustom<Card, CardInfo> {

}
