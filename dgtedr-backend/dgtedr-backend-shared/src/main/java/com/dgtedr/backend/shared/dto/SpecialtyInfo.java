package com.dgtedr.backend.shared.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.core.style.ToStringCreator;

import com.efs.core.dto.BaseInfo;

/**
 *
 * @author mbmartinez on 11 Jun 2018
 *
 */
public class SpecialtyInfo extends BaseInfo {

    @NotBlank
    private String name;
    private String description;

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("name", name)
                .append("desc", description);
    }

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
