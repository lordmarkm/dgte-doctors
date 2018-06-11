package com.dgtedr.backend.doctor.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.efs.core.jpa.model.BaseEntity;

/**
*
* @author mbmartinez on 11 Jun 2018
*
*/
@Entity(name = "subspecialty")
public class Subspecialty extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "subspec_name", nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
