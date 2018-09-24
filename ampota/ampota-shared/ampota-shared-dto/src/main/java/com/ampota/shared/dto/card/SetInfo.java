package com.ampota.shared.dto.card;

import xyz.quadx.shared.dto.BaseInfo;

public class SetInfo extends BaseInfo {

    private String code;
    private String name;
    private String setType;
    private String parentSetCode;
    private String iconUri;
    private String block;
    private String blockCode;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSetType() {
        return setType;
    }
    public void setSetType(String setType) {
        this.setType = setType;
    }
    public String getParentSetCode() {
        return parentSetCode;
    }
    public void setParentSetCode(String parentSetCode) {
        this.parentSetCode = parentSetCode;
    }
    public String getIconUri() {
        return iconUri;
    }
    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }
    public String getBlock() {
        return block;
    }
    public void setBlock(String block) {
        this.block = block;
    }
    public String getBlockCode() {
        return blockCode;
    }
    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

}
