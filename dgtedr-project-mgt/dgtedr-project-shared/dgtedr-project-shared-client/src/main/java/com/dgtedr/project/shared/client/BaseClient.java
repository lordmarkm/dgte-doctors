package com.dgtedr.project.shared.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.efs.core.dto.BaseInfo;

public interface BaseClient<D extends BaseInfo> {

    ResponseEntity<Page<D>> rqlSearch(String term, Pageable page);
    ResponseEntity<D> findOne(Long id);
    ResponseEntity<D> save(D project);

}
