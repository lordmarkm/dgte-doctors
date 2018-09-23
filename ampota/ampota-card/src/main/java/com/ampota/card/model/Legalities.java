package com.ampota.card.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ampota.shared.dto.card.Legality;

@Embeddable
public class Legalities {
    @Column(name = "leg_std")
    @Enumerated(EnumType.STRING)
    private Legality standard;
    @Column(name = "leg_modern")
    @Enumerated(EnumType.STRING)
    private Legality modern;
    @Column(name = "leg_legacy")
    @Enumerated(EnumType.STRING)
    private Legality legacy;
    @Column(name = "leg_pauper")
    @Enumerated(EnumType.STRING)
    private Legality pauper;
    @Column(name = "leg_vintage")
    @Enumerated(EnumType.STRING)
    private Legality vintage;
    @Column(name = "leg_cmdr")
    @Enumerated(EnumType.STRING)
    private Legality commander;
    @Column(name = "leg_duel")
    @Enumerated(EnumType.STRING)
    private Legality duel;
    public Legality getStandard() {
        return standard;
    }
    public void setStandard(Legality standard) {
        this.standard = standard;
    }
    public Legality getModern() {
        return modern;
    }
    public void setModern(Legality modern) {
        this.modern = modern;
    }
    public Legality getLegacy() {
        return legacy;
    }
    public void setLegacy(Legality legacy) {
        this.legacy = legacy;
    }
    public Legality getPauper() {
        return pauper;
    }
    public void setPauper(Legality pauper) {
        this.pauper = pauper;
    }
    public Legality getVintage() {
        return vintage;
    }
    public void setVintage(Legality vintage) {
        this.vintage = vintage;
    }
    public Legality getCommander() {
        return commander;
    }
    public void setCommander(Legality commander) {
        this.commander = commander;
    }
    public Legality getDuel() {
        return duel;
    }
    public void setDuel(Legality duel) {
        this.duel = duel;
    }

}
