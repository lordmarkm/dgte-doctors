package com.ampota.card.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.card.service.CardService;
import com.ampota.shared.dto.card.CardInfo;

import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/card")
public class CardResource extends BaseResource<CardInfo, CardService> {

}
