package com.ampota.user.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.dto.ShippingProviderInfo;
import com.ampota.user.service.ShippingProviderService;

import xyz.xpay.shared.web.BaseResource;
import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/shipping-provider")
public class ShippingProviderResource extends BaseResource<ShippingProviderInfo, ShippingProviderService> {

    @PostMapping
    public ResponseEntity<ShippingProviderInfo> save(@RequestBody @Valid ShippingProviderInfo shippingProvider) {
        return ResponseEntity.status(OK).body(service.saveInfo(shippingProvider));
    }

}
