package com.dgtedr.project.shared.encoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import java.lang.reflect.Type;

/**
 * This encoder adds support for pageable, which will be applied to the query parameters.
 */
public class PageableQueryEncoder implements Encoder {

    private static final Logger LOG = LoggerFactory.getLogger(PageableQueryEncoder.class);
    private final Encoder delegate;

    public PageableQueryEncoder(Encoder delegate){
        this.delegate = delegate;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        LOG.info("Checking encode. object={}", object);
        if(object instanceof Pageable){
            LOG.info("Encoding pageable.");
            Pageable pageable = (Pageable)object;
            template.query("page", pageable.getPageNumber() + "");
            template.query("size", pageable.getPageSize() + "");

            if(pageable.getSort() != null) {
                Collection<String> existingSorts = template.queries().get("sort");
                List<String> sortQueries  = existingSorts != null ? new ArrayList<>(existingSorts) : new ArrayList<>();
                for (Sort.Order order : pageable.getSort()) {
                    sortQueries.add(order.getProperty() + "," + order.getDirection());
                }
                template.query("sort", sortQueries);
            }

        }else{
            LOG.info("Not pageable. Ignoring... object={}", object);
            delegate.encode(object, bodyType, template);
        }
    }
}