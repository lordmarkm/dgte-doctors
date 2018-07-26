package com.dgtedr.backend.project.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.backend.project.service.ProjectService;
import com.dgtedr.project.shared.client.ProjectClient;
import com.dgtedr.project.shared.dto.ProjectInfo;
import com.mynt.core.web.BaseResource;

@RestController
@RequestMapping("/project")
public class ProjectResource extends BaseResource<ProjectInfo, ProjectService>
    implements ProjectClient {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectResource.class);

}
