package com.ampota.user.service;

import com.ampota.user.model.ShippingProvider;

import xyz.xpay.shared.jpa.service.XpayJpaService;

public interface ShippingProviderService extends XpayJpaService<ShippingProvider>, ShippingProviderServiceCustom {

}
