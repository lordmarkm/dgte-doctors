package com.ampota.card;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Encoder;
import xyz.quadx.xpay.shared.encoder.PageableQueryEncoder;
import xyz.quadx.xpay.shared.errordecoder.XpayErrorDecoder;
import xyz.quadx.xpay.shared.firebase.FirebaseTokenHolder;

@Configuration
public class FeignClientConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    //This is for encoding Pageable
    @Bean
    public Encoder feignEncoder() {
        return new PageableQueryEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    public RequestInterceptor firebaseTokenInserter() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("X-Firebase-Auth", FirebaseTokenHolder.get());
            }
        };
    }

    /**
     * Stop feign from turning all non-2xx feign calls into 500 response
     * @return
     */
    @Bean
    public XpayErrorDecoder myErrorDecoder() {
      return new XpayErrorDecoder();
    }

}
