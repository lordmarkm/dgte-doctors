package com.dgtedr.backend.project.service.impl;

import com.dgtedr.backend.project.model.Project;
import com.dgtedr.backend.project.service.ProjectService;
import com.dgtedr.backend.project.service.ProjectServiceCustom;
import com.dgtedr.project.shared.dto.ProjectInfo;
import com.efs.core.jpa.service.ExistJpaServiceCustomImpl;

public class ProjectServiceCustomImpl extends ExistJpaServiceCustomImpl<Project, ProjectInfo, ProjectService>
    implements ProjectServiceCustom {

}
