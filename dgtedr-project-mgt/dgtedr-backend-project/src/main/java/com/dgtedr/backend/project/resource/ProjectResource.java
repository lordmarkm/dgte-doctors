package com.dgtedr.backend.project.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.backend.project.service.ProjectService;
import com.dgtedr.project.shared.client.ProjectClient;
import com.dgtedr.project.shared.dto.ProjectInfo;
import com.dgtedr.project.shared.resource.BaseResource;

@RestController
@RequestMapping("/project")
public class ProjectResource extends BaseResource<ProjectInfo, ProjectService>
    implements ProjectClient {

}
