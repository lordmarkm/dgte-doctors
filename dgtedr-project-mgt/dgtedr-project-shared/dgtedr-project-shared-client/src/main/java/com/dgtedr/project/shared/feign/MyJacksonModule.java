package com.dgtedr.project.shared.feign;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class MyJacksonModule extends SimpleModule {

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(Page.class, PageMixIn.class);
    }
}