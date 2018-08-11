package com.dgtedr.project.shared.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author mbmartinez, Sep 27, 2017
 *
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProjectInfo.class, name = ProjectInfo.DTO_TYPE),
    @JsonSubTypes.Type(value = FundSourceInfo.class, name = FundSourceInfo.DTO_TYPE),
})
public abstract class BaseInfo extends com.efs.core.dto.BaseInfo {

    @ApiModelProperty(allowableValues = "project, fund_source, fund_contribution")
    public abstract String getType();

}
