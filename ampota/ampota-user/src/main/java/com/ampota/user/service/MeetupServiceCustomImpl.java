package com.ampota.user.service;

import com.ampota.shared.dto.MeetupInfo;
import com.ampota.user.model.Meetup;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class MeetupServiceCustomImpl extends XpayJpaServiceCustomImpl<Meetup, MeetupInfo, MeetupService>
    implements MeetupServiceCustom {

}
