package com.ampota.card.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ImageUris {

    @Column(name = "img_small")
    private String small;
    @Column(name = "img_normal")
    private String normal;
    @Column(name = "img_large")
    private String large;
    @Column(name = "img_png")
    private String png;
    @Column(name = "img_art_crop")
    private String artCrop;
    @Column(name = "img_border_crop")
    private String borderCrop;

    public String getSmall() {
        return small;
    }
    public void setSmall(String small) {
        this.small = small;
    }
    public String getNormal() {
        return normal;
    }
    public void setNormal(String normal) {
        this.normal = normal;
    }
    public String getLarge() {
        return large;
    }
    public void setLarge(String large) {
        this.large = large;
    }
    public String getPng() {
        return png;
    }
    public void setPng(String png) {
        this.png = png;
    }
    public String getArtCrop() {
        return artCrop;
    }
    public void setArtCrop(String artCrop) {
        this.artCrop = artCrop;
    }
    public String getBorderCrop() {
        return borderCrop;
    }
    public void setBorderCrop(String borderCrop) {
        this.borderCrop = borderCrop;
    }

}
