package com.dgtedr.backend.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.efs.core.jpa.model.BaseEntity;

@Entity(name = "fund_source")
public class FundSource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
