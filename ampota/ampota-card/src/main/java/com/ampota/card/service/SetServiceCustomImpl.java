package com.ampota.card.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.ampota.card.model.Set;
import com.ampota.card.util.SetParserUtil;
import com.ampota.shared.dto.card.SetInfo;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

import xyz.xpay.shared.jpa.service.XpayJpaServiceCustomImpl;

public class SetServiceCustomImpl extends XpayJpaServiceCustomImpl<Set, SetInfo, SetService>
    implements SetServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(SetServiceCustomImpl.class);

    @PostConstruct
    public void loadSetsIfDatabaseEmpty() throws ParseException, FileNotFoundException, IOException {
        if (repo.count() > 0) {
            return;
        }
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(new ClassPathResource("scryfall-all-sets.json").getInputStream(), "UTF-8"));
        reader.beginArray();
        List<Set> sets = Lists.newArrayList();
        while (reader.hasNext()) {
            LinkedTreeMap<String, Object> jo = gson.fromJson(reader, LinkedTreeMap.class);
            sets.add(SetParserUtil.parseSet(jo));
        }
        reader.endArray();
        reader.close();
        repo.saveAll(sets);
    }

}
