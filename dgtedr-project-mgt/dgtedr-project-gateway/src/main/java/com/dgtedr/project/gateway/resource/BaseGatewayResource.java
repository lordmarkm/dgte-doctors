package com.dgtedr.project.gateway.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.dgtedr.project.shared.client.BaseClient;
import com.efs.core.dto.BaseInfo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public abstract class BaseGatewayResource<D extends BaseInfo, C extends BaseClient<D>> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseGatewayResource.class);

    @Autowired
    protected C client;

    @ApiOperation(value = "Perform a parametrized search for this entity")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
        value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
        value = "Sorting criteria in the format: property(,asc|desc). "
                + "Default sort order is ascending. "
                + "Multiple sort criteria are supported.")
    })
    @GetMapping
    public ResponseEntity<Page<D>> rqlSearch(@RequestParam String term, Pageable page) {
        LOG.info("{}::findAll({}, {})", this.getClass().getSimpleName(), term, page);
        return client.rqlSearch(term, page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> findeOne(@PathVariable Long id) {
        LOG.info("{}::findeOne({})", this.getClass().getSimpleName(), id);
        return client.findOne(id);
    }

    @PostMapping
    public ResponseEntity<D> save(@RequestBody D project) {
        LOG.info("{}::save({})", this.getClass().getSimpleName(), project);
        ResponseEntity<D> retval = client.save(project);
        D data = retval.getBody();
        LOG.info("Save complete. data={}", data);
        return retval;
    }

}
