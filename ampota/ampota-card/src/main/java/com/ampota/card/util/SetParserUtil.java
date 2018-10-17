package com.ampota.card.util;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ampota.card.model.Set;
import com.google.gson.internal.LinkedTreeMap;

public class SetParserUtil {
    private static final Logger LOG = LoggerFactory.getLogger(SetParserUtil.class);
    private static long cardsParsed = 0L;

    private static final String JSON_KEY_BLOCK = "block";
    private static final String JSON_KEY_BLOCK_CODE = "block_code";
    private static final String JSON_KEY_CODE = "code";
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_ICON_URI = "icon_svg_uri";
    private static final String JSON_KEY_PARENT_SET_CODE = "parent_set_code";
    private static final String JSON_KEY_SET_TYPE = "set_type";
    private static final String JSON_KEY_RELEASED_AT = "released_at";

    public static Set parseSet(LinkedTreeMap<String, Object> setJson) {
        LOG.info("Parsing set. set name={}, total={}",  (String) setJson.get(JSON_KEY_NAME), ++cardsParsed);

        Set set = new Set();
        set.setBlock((String) setJson.get(JSON_KEY_BLOCK));
        set.setBlockCode((String) setJson.get(JSON_KEY_BLOCK_CODE));
        set.setCode((String) setJson.get(JSON_KEY_CODE));
        set.setIconUri((String) setJson.get(JSON_KEY_ICON_URI));
        set.setName((String) setJson.get(JSON_KEY_NAME));
        set.setParentSetCode((String) setJson.get(JSON_KEY_BLOCK_CODE));
        set.setSetType((String) setJson.get(JSON_KEY_SET_TYPE));
        set.setParentSetCode((String) setJson.get(JSON_KEY_PARENT_SET_CODE));
        try {
            set.setReleaseDate(DateTime.parse((String) setJson.get(JSON_KEY_RELEASED_AT)));
        } catch (Exception e) {
            LOG.error("Date parse failed! date={}", setJson.get(JSON_KEY_RELEASED_AT));
            set.setReleaseDate(null);
        }
        return set;
    }

}
