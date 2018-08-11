package com.dgtedr.backend.project.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;

import com.efs.core.jpa.model.BaseEntity;

@Entity(name = "funding")
public class Funding extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "depositor")
    private String depositor;

    @Digits(integer = 7, fraction = 2)
    @Column(name = "amount")
    private BigDecimal amount;

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
