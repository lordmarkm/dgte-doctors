package com.ampota.shared.dto;

import xyz.quadx.shared.dto.BaseInfo;

public class MeetupInfo extends BaseInfo {

    private String name;
    private AddressInfo address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public AddressInfo getAddress() {
        return address;
    }
    public void setAddress(AddressInfo address) {
        this.address = address;
    }

}
