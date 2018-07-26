package com.dgtedr.project.gateway.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgtedr.project.shared.client.FundSourceClient;
import com.dgtedr.project.shared.dto.FundSourceInfo;

@RestController
@RequestMapping("/api/fund-source")
public class FundSourceGatewayResource extends BaseGatewayResource<FundSourceInfo, FundSourceClient> {

}
