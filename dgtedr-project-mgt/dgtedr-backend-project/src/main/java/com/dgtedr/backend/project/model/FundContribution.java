package com.dgtedr.backend.project.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.efs.core.jpa.model.BaseEntity;

@Entity(name = "fund_contribution")
public class FundContribution extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fund_source_id")
    private FundSource fundSource;

    @Column(name = "amount", nullable = false, precision = 2, scale = 2)
    private BigDecimal amount;

    @Column(name = "fv_before", nullable = false, precision = 2, scale = 2)
    private BigDecimal fundValueBefore;

    @Column(name = "fv_after", nullable = false, precision = 2, scale = 2)
    private BigDecimal fundValueAfter;

    public FundSource getFundSource() {
        return fundSource;
    }

    public void setFundSource(FundSource fundSource) {
        this.fundSource = fundSource;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFundValueBefore() {
        return fundValueBefore;
    }

    public void setFundValueBefore(BigDecimal fundValueBefore) {
        this.fundValueBefore = fundValueBefore;
    }

    public BigDecimal getFundValueAfter() {
        return fundValueAfter;
    }

    public void setFundValueAfter(BigDecimal fundValueAfter) {
        this.fundValueAfter = fundValueAfter;
    }

}
