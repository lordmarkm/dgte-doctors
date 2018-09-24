package com.ampota.card.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "card")
public class Card extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "scryfall_id")
    private String scryfallId;

    @Column(name = "oracle_id")
    private String oracleId;

    @Column(name = "name")
    private String name;

    @Column(name = "lang")
    private String lang;

    @Column(name = "set_code")
    private String setCode;

    @Column(name = "usd")
    private BigDecimal usd;

    @Embedded
    private ImageUris imageUris;

    @Column(name = "mana_cost")
    private String manaCost;

    @Column(name = "cmc")
    private BigDecimal cmc;

    @Column(name = "typeLine")
    private String typeLine;

    @Column(name = "oracle_text")
    @Type(type = "text")
    private String oracleText;

    @ElementCollection
    @CollectionTable(name = "card_colors")
    private List<String> colors;

    @Embedded
    private Legalities legalities;

    @Column(name = "rarity")
    private String rarity;

    @Column(name = "artist")
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
    public BigDecimal getCmc() {
        return cmc;
    }
    public void setCmc(BigDecimal cmc) {
        this.cmc = cmc;
    }
    public List<String> getColors() {
        return colors;
    }
    public void setColors(List<String> colors) {
        this.colors = colors;
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
    public ImageUris getImageUris() {
        return imageUris;
    }
    public void setImageUris(ImageUris imageUris) {
        this.imageUris = imageUris;
    }
    public String getManaCost() {
        return manaCost;
    }
    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }
    public String getOracleText() {
        return oracleText;
    }
    public void setOracleText(String oracleText) {
        this.oracleText = oracleText;
    }
    public Legalities getLegalities() {
        return legalities;
    }
    public void setLegalities(Legalities legalities) {
        this.legalities = legalities;
    }
    public String getTypeLine() {
        return typeLine;
    }
    public void setTypeLine(String typeLine) {
        this.typeLine = typeLine;
    }
    public String getSetCode() {
        return setCode;
    }
    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

}
