package com.dgtedr.backend.project.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.backend.project.service.FundContributionService;
import com.dgtedr.project.shared.dto.FundContributionInfo;
import com.dgtedr.project.shared.resource.BaseResource;

@RestController
@RequestMapping("/fund-contribution")
public class FundContributionResource extends BaseResource<FundContributionInfo, FundContributionService> {

}
