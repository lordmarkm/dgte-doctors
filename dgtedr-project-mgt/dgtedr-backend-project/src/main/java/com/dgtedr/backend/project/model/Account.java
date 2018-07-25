package com.dgtedr.backend.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.efs.core.jpa.model.BaseEntity;

@Entity(name = "account")
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * If this is not empty, it's a top-level account
     */
    @ManyToOne(optional = true)
    @JoinColumn(name = "coa_id")
    private ChartOfAccounts chartOfAccount;

    /**
     * If this is not empty, it's a sub account. chartOfAccount and parent can't BOTH be null
     */
    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_id")
    private Account parent;

    @Column(name = "name", nullable = false)
    private String name;

    public ChartOfAccounts getChartOfAccount() {
        return chartOfAccount;
    }

    public void setChartOfAccount(ChartOfAccounts chartOfAccount) {
        this.chartOfAccount = chartOfAccount;
    }

    public Account getParent() {
        return parent;
    }

    public void setParent(Account parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
