package com.dgtedr.backend.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.efs.core.jpa.model.BaseEntity;

/**
 *
 * @author mbmartinez
 *
 */
@Entity(name = "project")
public class Project extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "proj_name", nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_source_id")
    private FundSource fundSource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
