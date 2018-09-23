package com.ampota.card.service;

import com.ampota.card.model.Card;

import xyz.xpay.shared.jpa.service.XpayJpaService;

public interface CardService extends XpayJpaService<Card>, CardServiceCustom {

}
