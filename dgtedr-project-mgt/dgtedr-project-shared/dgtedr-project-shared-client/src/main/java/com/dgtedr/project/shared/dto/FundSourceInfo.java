package com.dgtedr.project.shared.dto;

import org.springframework.core.style.ToStringCreator;

public class FundSourceInfo extends BaseInfo {

    public static final String DTO_TYPE = "fund_source";

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("type", DTO_TYPE);
    }

    @Override
    public String getType() {
        return DTO_TYPE;
    }

}
