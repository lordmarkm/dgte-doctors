package com.ampota.card.service.collection;

import com.ampota.card.model.collection.Bundle;
import com.ampota.shared.dto.card.collection.BundleInfo;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;
import static com.ampota.card.model.collection.QBundle.bundle;

public interface BundleServiceCustom extends XpayJpaServiceCustom<Bundle, BundleInfo> {

    default ImmutableMap<String, Path<?>> getFieldMapping() {
        return ImmutableMap.<String, Path<?>>builder().put("id", bundle.id)
                .put("forSale", bundle.forSale)
                .put("deleted", bundle.deleted)
                .put("cardDetails", bundle.card.name)
                .put("cardName", bundle.card.name)
                .put("standard", bundle.card.legalities.standard)
                .put("modern", bundle.card.legalities.modern)
                .put("legacy", bundle.card.legalities.legacy)
                .put("vintage", bundle.card.legalities.vintage)
                .put("pauper", bundle.card.legalities.pauper)
                .put("edh", bundle.card.legalities.commander)
                .put("duel", bundle.card.legalities.duel)
                .build();
    }

}
