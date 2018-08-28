package com.dgtedr.backend.project.service;

import com.dgtedr.backend.project.model.Project;
import com.dgtedr.project.shared.dto.ProjectInfo;
import com.efs.core.jpa.service.ExistJpaServiceCustom;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;
import static com.dgtedr.backend.project.model.QProject.project;

public interface ProjectServiceCustom extends ExistJpaServiceCustom<Project, ProjectInfo> {

    default ImmutableMap<String, Path<?>> getFieldMapping() {
        return ImmutableMap.of(
                    "id", project.id,
                    "deleted", project.deleted,
                    "name", project.name
                );
    }

}
