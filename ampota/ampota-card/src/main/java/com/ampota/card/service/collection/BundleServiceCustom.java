package com.ampota.card.service.collection;

import com.ampota.card.model.collection.Bundle;
import com.ampota.shared.dto.card.collection.BundleInfo;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;
import static com.ampota.card.model.collection.QBundle.bundle;

public interface BundleServiceCustom extends XpayJpaServiceCustom<Bundle, BundleInfo> {

    default ImmutableMap<String, Path<?>> getFieldMapping() {
        return ImmutableMap.of("id", bundle.id,
                "forSale", bundle.forSale,
                "deleted", bundle.deleted,
                "cardDetails", bundle.card.name,
                "cardName", bundle.card.name);
    }

}
