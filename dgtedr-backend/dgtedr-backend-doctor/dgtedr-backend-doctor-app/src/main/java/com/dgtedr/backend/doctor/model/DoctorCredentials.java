package com.dgtedr.backend.doctor.model;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Embeddable
public class DoctorCredentials {

    @ManyToMany
    @JoinTable(name = "doctor_x_specialty")
    private List<Specialty> specialties;

    @ManyToMany
    @JoinTable(name = "doctor_x_subspecialty")
    private List<Subspecialty> subspecialties;

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public List<Subspecialty> getSubspecialties() {
        return subspecialties;
    }

    public void setSubspecialties(List<Subspecialty> subspecialties) {
        this.subspecialties = subspecialties;
    }

}
