package com.dgtedr.backend.project.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.backend.project.service.FundSourceService;
import com.dgtedr.project.shared.client.FundSourceClient;
import com.dgtedr.project.shared.dto.FundSourceInfo;
import com.dgtedr.project.shared.resource.BaseResource;

@RestController
@RequestMapping("/fund-source")
public class FundSourceResource extends BaseResource<FundSourceInfo, FundSourceService>
    implements FundSourceClient {

    private static final Logger LOG = LoggerFactory.getLogger(FundSourceResource.class);

}
