package com.ampota.shared.dto;

import javax.validation.constraints.NotEmpty;

import xyz.quadx.shared.dto.BaseInfo;

public class BankInfo extends BaseInfo {

    @NotEmpty private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
