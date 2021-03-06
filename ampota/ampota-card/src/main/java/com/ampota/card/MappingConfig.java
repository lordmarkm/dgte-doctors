package com.ampota.card;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ampota.card.model.transaction.Order;
import com.ampota.card.model.transaction.Transaction;
import com.ampota.shared.dto.transaction.OrderInfo;
import com.ampota.shared.dto.transaction.TransactionInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import xyz.quadx.shared.dto.BaseInfo;
import xyz.xpay.shared.jpa.model.BaseEntity;
import xyz.xpay.shared.util.DateUtil;

@Configuration
public class MappingConfig {

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();

        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(BaseEntity.class, BaseInfo.class)
                        .fields("createdDate", "createdDate", copyByReference())
                        .fields("updatedDate", "updatedDate", copyByReference());

                //Transaction -> TransactionInfo & VV
                mapping(Transaction.class, TransactionInfo.class, TypeMappingOptions.oneWay());
                mapping(TransactionInfo.class, Transaction.class, TypeMappingOptions.oneWay())
                        .fields("buyerProfile.id", "buyerId")
                        .fields("buyerProfile.displayName", "buyerName")
                        .fields("buyerProfile.fbLink", "buyerLink")
                        .fields("sellerProfile.id", "sellerId")
                        .fields("sellerProfile.displayName", "sellerName")
                        .fields("sellerProfile.fbLink", "sellerLink")
                        .fields("meetup.id", "meetupId");

                mapping(Order.class, OrderInfo.class, TypeMappingOptions.oneWay());
                mapping(OrderInfo.class, Order.class, TypeMappingOptions.oneWay())
                        .fields("bundle.id", "bundleId");
            }
        });

        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Bean
    public Gson gson() {
        Gson gson = new GsonBuilder()
                .setDateFormat(DateUtil.DATE_TIME_FORMAT)
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
