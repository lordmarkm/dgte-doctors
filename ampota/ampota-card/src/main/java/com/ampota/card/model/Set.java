package com.ampota.card.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.joda.time.DateTime;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "set")
public class Set extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Column(name = "set_code", unique = true)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "set_type")
    private String setType;
    @Column(name = "parent_set_code")
    private String parentSetCode;
    @Column(name = "icon_uri")
    private String iconUri;
    @Column(name = "block")
    private String block;
    @Column(name = "block_code")
    private String blockCode;
    @Column(name = "release_date")
    private DateTime releaseDate;

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
    public DateTime getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(DateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

}
