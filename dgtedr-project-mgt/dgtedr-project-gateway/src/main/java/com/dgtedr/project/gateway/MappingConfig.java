package com.dgtedr.project.gateway;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.dgtedr.project.shared.feign.MyJacksonModule;
import com.mynt.core.util.DateUtil;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

@Configuration
public class MappingConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new MyJacksonModule());
        mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Bean
    public Gson gson() {
        Gson gson = new GsonBuilder()
                .setDateFormat("MM/dd/yyyy hh:mm")
                .registerTypeAdapter(DateTime.class, new JsonSerializer<DateTime>() {
                    @Override
                    public JsonElement serialize(DateTime src, Type type, JsonSerializationContext context) {
                        return new JsonPrimitive(ISODateTimeFormat.dateTime().print(src.withZone(DateTimeZone.forID(DateUtil.TIMEZONE_ID))));
                    }
                })
                .setPrettyPrinting()
                .create();
        return gson;
    }

}
