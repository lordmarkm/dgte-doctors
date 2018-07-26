package com.dgtedr.project.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dgtedr.project.shared.dto.ProjectInfo;

/**
 * No @RequestMapping on type level otherwise spring will create a resource bean for this interface.
 *
 * @author mbmartinez on 26 Jul 2018
 *
 */
@FeignClient("project")
public interface ProjectClient extends BaseClient<ProjectInfo> {

    @GetMapping("/project")
    ResponseEntity<Page<ProjectInfo>> rqlSearch(@RequestParam String term, @RequestBody Pageable page);

    @GetMapping("/project/{id}")
    ResponseEntity<ProjectInfo> findOne(@PathVariable Long id);

    @PostMapping("/project")
    ResponseEntity<ProjectInfo> save(@RequestBody ProjectInfo project);

}
