package com.dgtedr.project.shared.dto;

import org.springframework.core.style.ToStringCreator;

/**
 *
 * @author mbmartinez
 *
 */
public class ProjectInfo extends BaseInfo {

    public static final String DTO_TYPE = "project";
    private String name;
    private FundSourceInfo fundSource;

    @Override
    public String getType() {
        return DTO_TYPE;
    }

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("name", name)
                .append("fund source", fundSource);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FundSourceInfo getFundSource() {
        return fundSource;
    }

    public void setFundSource(FundSourceInfo fundSource) {
        this.fundSource = fundSource;
    }



}
