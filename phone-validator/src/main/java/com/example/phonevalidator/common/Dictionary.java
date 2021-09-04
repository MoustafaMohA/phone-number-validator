package com.example.phonevalidator.common;

import java.util.Map;

/**
 * The class acts as in memory database, it could be a SQL table or a configuration JSON file that contains all data needed,
 * If we need to add new country this is the only file to modified, also if we need for whatever reason to changes validation
 * regex this is the only file to be modified.
 */
public class Dictionary {
    public static final String VALIDATOR_KEY = "validator";
    public static final String COUNTRY_NAME_KEY = "name";

    public static final Map<String, Map<String, String>> CONFIG =
            Map.of("+237", Map.of(COUNTRY_NAME_KEY, "Cameroon", VALIDATOR_KEY, "\\(237\\) ?[2368]\\d{7,8}$"),
                    "+251", Map.of(COUNTRY_NAME_KEY, "Ethiopia", VALIDATOR_KEY, "\\(251\\) ?[1-59]\\d{8}$"),
                    "+212", Map.of(COUNTRY_NAME_KEY, "Morocco", VALIDATOR_KEY, "\\(212\\) ?\\[5-9]\\d{8}$"),
                    "+258", Map.of(COUNTRY_NAME_KEY, "Mozambique", VALIDATOR_KEY, "\\(258\\) ?[28]\\d{7,8}$"),
                    "+256", Map.of(COUNTRY_NAME_KEY, "Uganda", VALIDATOR_KEY, "\\(256\\) ?\\d{9}$"));
}
