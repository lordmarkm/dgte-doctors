package com.dgtedr.project.shared.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dgtedr.project.shared.dto.ProjectInfo;

@FeignClient("project")
public interface ProjectClient {

    @GetMapping("/specialty/{id}")
    ResponseEntity<ProjectInfo> findOne(@PathVariable Long id);

}
