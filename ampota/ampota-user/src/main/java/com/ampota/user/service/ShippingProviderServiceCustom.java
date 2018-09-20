package com.ampota.user.service;

import com.ampota.shared.dto.ShippingProviderInfo;
import com.ampota.user.model.ShippingProvider;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;

public interface ShippingProviderServiceCustom extends XpayJpaServiceCustom<ShippingProvider, ShippingProviderInfo> {

}
