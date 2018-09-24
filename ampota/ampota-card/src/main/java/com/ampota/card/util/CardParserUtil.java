package com.ampota.card.util;

import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ampota.card.model.Card;
import com.ampota.card.model.ImageUris;
import com.ampota.card.model.Legalities;
import com.ampota.shared.dto.card.Legality;
import com.google.api.client.util.Lists;
import com.google.gson.internal.LinkedTreeMap;

public class CardParserUtil {
    private static final Logger LOG = LoggerFactory.getLogger(CardParserUtil.class);
    private static long cardsParsed = 0L;

    private static final String JSON_KEY_ARTIST = "artist";
    private static final String JSON_KEY_SET_CODE = "set";
    private static final String JSON_KEY_CMC = "cmc";
    private static final String JSON_KEY_LEGALITIES = "legalities";
    private static final String JSON_KEY_COMMANDER = "commander";
    private static final String JSON_KEY_DUEL = "duel";
    private static final String JSON_KEY_LEGACY = "legacy";
    private static final String JSON_KEY_MODERN = "modern";
    private static final String JSON_KEY_PAUPER = "pauper";
    private static final String JSON_KEY_STANDARD = "standard";
    private static final String JSON_KEY_VINTAGE = "vintage";
    private static final String JSON_KEY_COLORS = "colors";
    private static final String JSON_KEY_IMAGE_URIS = "image_uris";
    private static final String JSON_KEY_LANG = "lang";
    private static final String JSON_KEY_SMALL = "small";
    private static final String JSON_KEY_NORMAL = "normal";
    private static final String JSON_KEY_LARGE = "large";
    private static final String JSON_KEY_PNG = "png";
    private static final String JSON_KEY_ART_CROP = "art_crop";
    private static final String JSON_KEY_BORDER_CROP = "border_crop";
    private static final String JSON_KEY_MANA_COST = "mana_cost";
    private static final String JSON_KEY_TYPE_LINE = "type_line";
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_ORACLE_ID = "oracle_id";
    private static final String JSON_KEY_ORACLE_TEXT = "oracle_text";
    private static final String JSON_KEY_RARITY = "rarity";
    private static final String JSON_KEY_ID = "id";

    public static Card parseCard(LinkedTreeMap<String, Object> cardJson) {
        LOG.info("Parsing card. card name={}, total={}",  (String) cardJson.get(JSON_KEY_NAME), ++cardsParsed);

        Card card = new Card();
        card.setArtist((String) cardJson.get(JSON_KEY_ARTIST));
        card.setSetCode((String) cardJson.get(JSON_KEY_SET_CODE));
        card.setCmc(new BigDecimal((double) cardJson.get(JSON_KEY_CMC)));
        card.setLang((String) cardJson.get(JSON_KEY_LANG));
        card.setManaCost((String) cardJson.get(JSON_KEY_MANA_COST));
        card.setTypeLine((String) cardJson.get(JSON_KEY_TYPE_LINE));
        card.setName((String) cardJson.get(JSON_KEY_NAME));
        card.setOracleId((String) cardJson.get(JSON_KEY_ORACLE_ID));
        card.setOracleText((String) cardJson.get(JSON_KEY_ORACLE_TEXT));
        card.setRarity((String) cardJson.get(JSON_KEY_RARITY));
        card.setScryfallId((String) cardJson.get(JSON_KEY_ID));

        LinkedTreeMap<String, Object> legalitiesJson = (LinkedTreeMap<String, Object>) cardJson.get(JSON_KEY_LEGALITIES);
        Legalities legalities = new Legalities();
        legalities.setCommander(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_COMMANDER)));
        legalities.setDuel(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_DUEL)));
        legalities.setLegacy(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_LEGACY)));
        legalities.setModern(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_MODERN)));
        legalities.setPauper(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_PAUPER)));
        legalities.setStandard(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_STANDARD)));
        legalities.setVintage(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_VINTAGE)));
        card.setLegalities(legalities);

        List<String> colorJsonArray = (List<String>) cardJson.get(JSON_KEY_COLORS);
        List<String> colors = Lists.newArrayList();
        if (null != colorJsonArray) {
            for (Object o : colorJsonArray) {
                colors.add((String) o);
            }
        }
        card.setColors(colors);

        LinkedTreeMap<String, Object> imageUrisJson = (LinkedTreeMap<String, Object>) cardJson.get(JSON_KEY_IMAGE_URIS);
        ImageUris imgs = new ImageUris();
        if (null != imageUrisJson) {
            imgs.setSmall((String) imageUrisJson.get(JSON_KEY_SMALL));
            imgs.setNormal((String) imageUrisJson.get(JSON_KEY_NORMAL));
            imgs.setLarge((String) imageUrisJson.get(JSON_KEY_LARGE));
            imgs.setPng((String) imageUrisJson.get(JSON_KEY_PNG));
            imgs.setArtCrop((String) imageUrisJson.get(JSON_KEY_ART_CROP));
            imgs.setBorderCrop((String) imageUrisJson.get(JSON_KEY_BORDER_CROP));
        }
        card.setImageUris(imgs);

        return card;
    }

    public static Card parseCard(JSONObject cardJson) {
        Card card = new Card();
        card.setArtist((String) cardJson.get(JSON_KEY_ARTIST));
        card.setCmc(new BigDecimal((double) cardJson.get(JSON_KEY_CMC)));
        card.setLang((String) cardJson.get(JSON_KEY_LANG));
        card.setManaCost((String) cardJson.get(JSON_KEY_MANA_COST));
        card.setTypeLine((String) cardJson.get(JSON_KEY_TYPE_LINE));
        card.setName((String) cardJson.get(JSON_KEY_NAME));
        card.setOracleId((String) cardJson.get(JSON_KEY_ORACLE_ID));
        card.setOracleText((String) cardJson.get(JSON_KEY_ORACLE_TEXT));
        card.setRarity((String) cardJson.get(JSON_KEY_RARITY));
        card.setScryfallId((String) cardJson.get(JSON_KEY_ID));

        JSONObject legalitiesJson = (JSONObject) cardJson.get(JSON_KEY_LEGALITIES);
        Legalities legalities = new Legalities();
        legalities.setCommander(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_COMMANDER)));
        legalities.setDuel(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_DUEL)));
        legalities.setLegacy(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_LEGACY)));
        legalities.setModern(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_MODERN)));
        legalities.setPauper(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_PAUPER)));
        legalities.setStandard(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_STANDARD)));
        legalities.setVintage(Legality.valueOf((String) legalitiesJson.get(JSON_KEY_VINTAGE)));
        card.setLegalities(legalities);

        JSONArray colorJsonArray = (JSONArray) cardJson.get(JSON_KEY_COLORS);
        List<String> colors = Lists.newArrayList();
        for (Object o : colorJsonArray) {
            colors.add((String) o);
        }
        card.setColors(colors);

        JSONObject imageUrisJson = (JSONObject) cardJson.get(JSON_KEY_IMAGE_URIS);
        ImageUris imgs = new ImageUris();
        imgs.setSmall((String) imageUrisJson.get(JSON_KEY_SMALL));
        imgs.setNormal((String) imageUrisJson.get(JSON_KEY_NORMAL));
        imgs.setLarge((String) imageUrisJson.get(JSON_KEY_LARGE));
        imgs.setPng((String) imageUrisJson.get(JSON_KEY_PNG));
        imgs.setArtCrop((String) imageUrisJson.get(JSON_KEY_ART_CROP));
        imgs.setBorderCrop((String) imageUrisJson.get(JSON_KEY_BORDER_CROP));
        card.setImageUris(imgs);

        return card;
    }

}
