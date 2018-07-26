package com.dgtedr.project.gateway.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.project.shared.client.ProjectClient;
import com.dgtedr.project.shared.dto.ProjectInfo;

@RestController
@RequestMapping("/api/project")
public class ProjectGatewayResource extends BaseGatewayResource<ProjectInfo, ProjectClient> {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectGatewayResource.class);

    @GetMapping
    public ResponseEntity<Page<ProjectInfo>> findAll(@RequestParam String term, Pageable page) {
        LOG.info("ProjectGatewayResource::findAll({}, {})", term, page);
        return client.rqlSearch(term, page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectInfo> findeOne(@PathVariable Long id) {
        LOG.info("ProjectGatewayResource::findeOne({})", id);
        return client.findOne(id);
    }

    @PostMapping
    public ResponseEntity<ProjectInfo> save(@RequestBody ProjectInfo project) {
        LOG.info("ProjectGatewayResource::save({})", project);
        return client.save(project);
    }

}
