package com.dgtedr.backend.doctor.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import com.efs.core.jpa.model.BaseEntity;

/**
 *
 * @author mbmartinez on 11 Jun 2018
 *
 */
@Entity(name = "specialty")
public class Specialty extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "spec_name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
