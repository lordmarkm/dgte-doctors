package com.ampota.card.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.ampota.card.model.Card;
import com.ampota.card.util.CardParserUtil;
import com.google.gson.internal.LinkedTreeMap;

public class CardProcessor implements ItemProcessor<LinkedTreeMap, Card> {
    private static final Logger LOG = LoggerFactory.getLogger(CardProcessor.class);
    @Override
    public Card process(LinkedTreeMap item) throws Exception {
        return CardParserUtil.parseCard(item);
    }
}