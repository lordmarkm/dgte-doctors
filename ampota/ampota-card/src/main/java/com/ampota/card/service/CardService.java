package com.ampota.card.service;

import java.util.Optional;

import com.ampota.card.model.Card;

import xyz.xpay.shared.jpa.service.XpayJpaService;

public interface CardService extends XpayJpaService<Card>, CardServiceCustom {

    Optional<Card> findByScryfallId(String scryfallId);

}
