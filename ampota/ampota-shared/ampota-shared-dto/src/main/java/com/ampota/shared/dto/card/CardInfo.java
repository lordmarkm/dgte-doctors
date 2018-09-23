package com.ampota.shared.dto.card;

import java.math.BigDecimal;
import java.util.List;

import xyz.quadx.shared.dto.BaseInfo;

public class CardInfo extends BaseInfo {

    private String scryfallId;
    private String oracleId;
    private String name;
    private String lang;
    private BigDecimal usd;
    private ImageUrisInfo imageUris;
    private String manaCost;
    private BigDecimal cmc;
    private String typeLine;
    private String oracleText;
    private List<String> colors;
    private LegalitiesInfo legalities;
    private String rarity;
    private String artist;

    public String getScryfallId() {
        return scryfallId;
    }
    public void setScryfallId(String scryfallId) {
        this.scryfallId = scryfallId;
    }
    public String getOracleId() {
        return oracleId;
    }
    public void setOracleId(String oracleId) {
        this.oracleId = oracleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    public BigDecimal getUsd() {
        return usd;
    }
    public void setUsd(BigDecimal usd) {
        this.usd = usd;
    }
    public ImageUrisInfo getImageUris() {
        return imageUris;
    }
    public void setImageUris(ImageUrisInfo imageUris) {
        this.imageUris = imageUris;
    }
    public String getManaCost() {
        return manaCost;
    }
    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }
    public BigDecimal getCmc() {
        return cmc;
    }
    public void setCmc(BigDecimal cmc) {
        this.cmc = cmc;
    }
    public String getTypeLine() {
        return typeLine;
    }
    public void setTypeLine(String typeLine) {
        this.typeLine = typeLine;
    }
    public String getOracleText() {
        return oracleText;
    }
    public void setOracleText(String oracleText) {
        this.oracleText = oracleText;
    }
    public List<String> getColors() {
        return colors;
    }
    public void setColors(List<String> colors) {
        this.colors = colors;
    }
    public LegalitiesInfo getLegalities() {
        return legalities;
    }
    public void setLegalities(LegalitiesInfo legalities) {
        this.legalities = legalities;
    }
    public String getRarity() {
        return rarity;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

}
