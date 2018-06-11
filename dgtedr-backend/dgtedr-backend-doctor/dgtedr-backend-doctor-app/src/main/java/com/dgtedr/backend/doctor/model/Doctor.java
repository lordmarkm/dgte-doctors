package com.dgtedr.backend.doctor.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.dgtedr.backend.shared.model.Name;
import com.efs.core.jpa.model.BaseEntity;

/**
 *
 * @author mbmartinez on 11 Jun 2018
 *
 */
@Entity(name = "doctor")
public class Doctor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    private Name name;

    @Embedded
    private DoctorCredentials credentials;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public DoctorCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(DoctorCredentials credentials) {
        this.credentials = credentials;
    }

}
