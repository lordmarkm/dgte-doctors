package com.ampota.user.service;

import com.ampota.shared.dto.ShippingProviderInfo;
import com.ampota.user.model.ShippingProvider;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class ShippingProviderServiceCustomImpl extends XpayJpaServiceCustomImpl<ShippingProvider, ShippingProviderInfo, ShippingProviderService>
    implements ShippingProviderServiceCustom {

}
