package com.dgtedr.project.gateway.resource;

//import static org.springframework.http.HttpStatus.OK;
//import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.dgtedr.project.shared.client.BaseClient;
import com.efs.core.dto.BaseInfo;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;

public abstract class BaseGatewayResource<D extends BaseInfo, C extends BaseClient<D>> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseGatewayResource.class);

    @Autowired
    protected C client;

//    @ApiOperation(value = "Perform a parametrized search for this entity")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
//                value = "Results page you want to retrieve (0..N)"),
//        @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
//                value = "Number of records per page."),
//        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
//                value = "Sorting criteria in the format: property(,asc|desc). "
//                        + "Default sort order is ascending. "
//                        + "Multiple sort criteria are supported.")
//    })
//    @RequestMapping(method = GET)
//    public ResponseEntity<Page<D>> rqlSearch(
//            @RequestParam(required = false) @ApiParam(value = "The search term, example: name==test;deleted==false") String term,
//            Pageable page) {
//        LOG.debug("{}::rqlSearch({}, {})", this.getClass().getSimpleName(), term, page);
//        return null;
//    }

}
