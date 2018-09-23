package com.ampota.card.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.ampota.card.model.Card;
import com.ampota.card.util.CardParserUtil;
import com.ampota.shared.dto.card.CardInfo;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class CardServiceCustomImpl extends XpayJpaServiceCustomImpl<Card, CardInfo, CardService>
    implements CardServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(CardServiceCustomImpl.class);

    @PostConstruct
    public void loadCardsIfDatabaseEmpty() throws ParseException, FileNotFoundException, IOException {
        if (repo.count() == 0) {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(new FileReader(new ClassPathResource("scryfall-all-cards.json").getFile()));

            for (Object jsonObject : array) {
                JSONObject cardJson = (JSONObject) jsonObject;
                Card card = CardParserUtil.parseCard(cardJson);
                repo.save(card);
            }
        }
    }

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(new FileReader(new ClassPathResource("scryfall-1-card.json").getFile()));

        for (Object jsonObject : array) {
            JSONObject card = (JSONObject) jsonObject;
            LOG.info("Processing card. card={}", card);
            Card parsed = CardParserUtil.parseCard(card);
            LOG.info("Parsed card: " + parsed);
        }
    }

}
