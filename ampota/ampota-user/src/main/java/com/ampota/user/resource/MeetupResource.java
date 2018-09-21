package com.ampota.user.resource;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ampota.shared.dto.MeetupInfo;
import com.ampota.user.service.MeetupService;

import xyz.xpay.shared.web.BaseResource;

@RestController
@RequestMapping("/api/meetup")
public class MeetupResource extends BaseResource<MeetupInfo, MeetupService> {

    @PostMapping
    public ResponseEntity<MeetupInfo> save(@RequestBody @Valid MeetupInfo meetup) {
        return ResponseEntity.status(OK).body(service.saveInfo(meetup));
    }

}
