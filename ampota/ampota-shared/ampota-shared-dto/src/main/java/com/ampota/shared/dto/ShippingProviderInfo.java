package com.ampota.shared.dto;

import javax.validation.constraints.NotEmpty;

import xyz.quadx.shared.dto.BaseInfo;

public class ShippingProviderInfo extends BaseInfo {

    @NotEmpty private String name;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
