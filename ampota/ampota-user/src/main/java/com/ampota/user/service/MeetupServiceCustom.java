package com.ampota.user.service;

import com.ampota.shared.dto.MeetupInfo;
import com.ampota.user.model.Meetup;
import com.google.common.collect.ImmutableMap;
import com.querydsl.core.types.Path;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustom;
import static com.ampota.user.model.QMeetup.meetup;

public interface MeetupServiceCustom extends XpayJpaServiceCustom<Meetup, MeetupInfo> {

    default ImmutableMap<String, Path<?>> getFieldMapping() {
        return ImmutableMap.of("id", meetup.id,
                "name", meetup.name);
    }

}
