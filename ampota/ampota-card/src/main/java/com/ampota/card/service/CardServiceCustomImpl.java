package com.ampota.card.service;

import static com.ampota.card.model.QCard.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

import com.ampota.card.model.Card;
import com.ampota.card.util.CardParserUtil;
import com.ampota.shared.dto.card.CardInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class CardServiceCustomImpl extends XpayJpaServiceCustomImpl<Card, CardInfo, CardService>
    implements CardServiceCustom {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void loadCardsIfDatabaseEmpty() throws ParseException, FileNotFoundException, IOException {
        if (repo.count() > 0) {
            return;
        }
        Gson gson = new Gson();
        FileInputStream fis = new FileInputStream(new File(Paths.get("scryfall_bulk_json", "scryfall-all-cards.json").toString()));
        JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));
        reader.beginArray();
        List<Card> cards = Lists.newArrayList();
        while (reader.hasNext()) {
            LinkedTreeMap<String, Object> jo = gson.fromJson(reader, LinkedTreeMap.class);
            cards.add(CardParserUtil.parseCard(jo));
        }
        reader.endArray();
        reader.close();
        repo.saveAll(cards);
    }

    @Override
    public Set<String> findUniqueNameLike(String term) {
        if (null == term || Strings.isNullOrEmpty(term.trim())) {
            return Sets.newHashSet();
        }

        JPAQuery<String> query = new JPAQuery<>(em);
        List<String> names = query.from(card)
                .where(card.name.startsWithIgnoreCase(term))
                .groupBy(card.name)
                .select(Projections.constructor(String.class, card.name))
                .limit(10)
                .fetch();
        return Sets.newHashSet(names);
    }

}
