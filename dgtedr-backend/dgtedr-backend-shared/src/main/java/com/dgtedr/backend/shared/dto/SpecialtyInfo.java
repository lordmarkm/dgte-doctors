package com.dgtedr.backend.shared.dto;

import com.efs.core.dto.BaseInfo;

/**
 *
 * @author mbmartinez on 11 Jun 2018
 *
 */
public class SpecialtyInfo extends BaseInfo {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
