package com.ampota.user.service;

import com.ampota.user.model.Meetup;

import xyz.xpay.shared.jpa.service.XpayJpaService;

public interface MeetupService extends XpayJpaService<Meetup>, MeetupServiceCustom {

}
