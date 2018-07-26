package com.dgtedr.backend.project.service;

import com.dgtedr.backend.project.model.Project;
import com.efs.core.jpa.service.ExistJpaService;

public interface ProjectService extends ExistJpaService<Project>, ProjectServiceCustom {

}
