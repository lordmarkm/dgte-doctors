package com.ampota.card.service;

import com.ampota.card.model.Card;
import com.ampota.shared.dto.card.CardInfo;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import static com.ampota.card.model.QCard.card;

import java.util.Set;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;

public interface CardServiceCustom extends XpayJpaServiceCustom<Card, CardInfo> {

    default ImmutableMap<String, Path<?>> getFieldMapping() {
        return ImmutableMap.of("id", card.id,
                "name", card.name,
                "lang", card.lang);
    }

    Set<String> findUniqueNameLike(String term);

}
