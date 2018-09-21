package com.ampota.user.resource;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.dto.BankInfo;
import com.ampota.user.service.BankService;

import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/bank")
public class BankResource extends BaseResource<BankInfo, BankService> {

    @PostMapping
    public ResponseEntity<BankInfo> save(@RequestBody @Valid BankInfo bank) {
        return ResponseEntity.status(OK).body(service.saveInfo(bank));
    }

}
