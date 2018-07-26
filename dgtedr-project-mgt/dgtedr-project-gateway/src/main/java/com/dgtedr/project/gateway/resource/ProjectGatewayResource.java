package com.dgtedr.project.gateway.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.project.shared.client.ProjectClient;
import com.dgtedr.project.shared.dto.ProjectInfo;

@RestController
@RequestMapping("/api/project")
public class ProjectGatewayResource extends BaseGatewayResource<ProjectInfo, ProjectClient> {

}
