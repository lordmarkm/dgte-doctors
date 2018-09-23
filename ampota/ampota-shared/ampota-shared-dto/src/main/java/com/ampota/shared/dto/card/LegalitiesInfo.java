package com.ampota.shared.dto.card;

public class LegalitiesInfo {

    private Legality standard;
    private Legality modern;
    private Legality legacy;
    private Legality pauper;
    private Legality vintage;
    private Legality commander;
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
