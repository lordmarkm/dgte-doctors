package com.ampota.user.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.dto.ShippingProviderInfo;

import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/shipping-provider")
public class ShippingProviderResource extends BaseResource<ShippingProviderInfo, ShippingProviderService> {

}
