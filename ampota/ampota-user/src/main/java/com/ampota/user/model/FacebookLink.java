package com.ampota.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.core.style.ToStringCreator;

import xyz.xpay.shared.jpa.model.BaseEntity;

@Entity(name = "fb_link")
public class FacebookLink extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "fb_link")
    private String fbLink;

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("un", username)
                .append("fbl", fbLink)
                .toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

}
