package com.ampota.user.resource.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.dto.BankInfo;
import com.ampota.user.service.BankService;

import xyz.xpay.shared.web.BaseResource;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/admin/bank")
public class BankAdminResource extends BaseResource<BankInfo, BankService> {

    @PostMapping
    public ResponseEntity<BankInfo> saveBank(@RequestBody BankInfo bank) {
        return ResponseEntity.status(OK).body(service.saveInfo(bank));
    }

}
