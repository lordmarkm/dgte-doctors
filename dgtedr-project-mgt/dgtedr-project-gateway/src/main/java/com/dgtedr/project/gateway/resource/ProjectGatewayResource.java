package com.dgtedr.project.gateway.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.project.shared.client.ProjectClient;
import com.dgtedr.project.shared.dto.ProjectInfo;

@RestController
@RequestMapping("/api/project")
public class ProjectGatewayResource extends BaseGatewayResource<ProjectInfo, ProjectClient> {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectGatewayResource.class);

    @Autowired
    private ProjectClient projectClient;

    /**
     * For a reason I don't understand yet, delegating this to BaseGatewayResource will cause the response data to be null.
     */
    @Override
    @PostMapping
    public ResponseEntity<ProjectInfo> save(@RequestBody ProjectInfo project) {
        LOG.info("Custom {}::save({})", this.getClass().getSimpleName(), project);
        ResponseEntity<ProjectInfo> retval = projectClient.save(project);
        ProjectInfo data = retval.getBody();
        LOG.info("Custom Save complete. data={}", data);
        return retval;
    }

}
