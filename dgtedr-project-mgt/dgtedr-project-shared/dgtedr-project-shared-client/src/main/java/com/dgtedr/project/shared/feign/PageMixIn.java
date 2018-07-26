package com.dgtedr.project.shared.feign;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = SimplePageImpl.class)
public interface PageMixIn {
}