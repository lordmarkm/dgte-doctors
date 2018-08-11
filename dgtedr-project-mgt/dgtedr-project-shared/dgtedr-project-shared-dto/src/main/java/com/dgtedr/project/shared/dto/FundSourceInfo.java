package com.dgtedr.project.shared.dto;

import org.springframework.core.style.ToStringCreator;

public class FundSourceInfo extends BaseInfo {

    public static final String DTO_TYPE = "fund_source";

    private String name;

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("name", name)
                .append("type", DTO_TYPE);
    }

    @Override
    public String getType() {
        return DTO_TYPE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
