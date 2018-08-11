package com.dgtedr.project.shared.dto;

import org.springframework.core.style.ToStringCreator;

import com.efs.core.dto.BaseInfo;

/**
 *
 * @author mbmartinez
 *
 */
public class ProjectInfo extends BaseInfo {

    private String name;

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("name", name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
