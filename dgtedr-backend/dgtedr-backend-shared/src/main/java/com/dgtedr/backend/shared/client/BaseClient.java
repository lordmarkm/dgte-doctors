package com.dgtedr.backend.shared.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * extends BaseClient<SpecialtyInfo> doesn't work. I don't get it.
 * @author mbmartinez on 14 Jun 2018
 * @param <D>
 */
@RequestMapping
public interface BaseClient<D> {

    @GetMapping("/{id}")
    ResponseEntity<D> findOne(@PathVariable Long id);

}
