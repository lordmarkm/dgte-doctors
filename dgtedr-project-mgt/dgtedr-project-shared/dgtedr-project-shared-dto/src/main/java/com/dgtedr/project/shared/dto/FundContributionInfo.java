package com.dgtedr.project.shared.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.springframework.core.style.ToStringCreator;

public class FundContributionInfo extends BaseInfo {

    public static final String DTO_TYPE = "fund_contribution";

    @Min(0)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal amount;
    private BigDecimal fundValueBefore;
    private BigDecimal fundValueAfter;

    @Override
    public String getType() {
        return DTO_TYPE;
    }

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("amt", amount)
                .append("fund val b4", fundValueBefore)
                .append("fund val after", fundValueAfter);
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
