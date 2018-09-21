package com.ampota.user.service;

import com.ampota.shared.dto.MeetupInfo;
import com.ampota.user.model.Meetup;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;

public interface MeetupServiceCustom extends XpayJpaServiceCustom<Meetup, MeetupInfo> {

}
