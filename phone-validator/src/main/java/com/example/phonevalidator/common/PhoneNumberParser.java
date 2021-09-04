package com.example.phonevalidator.common;

import java.util.HashMap;
import java.util.Map;

public class PhoneNumberParser {
    public static final String COUNTRY_CODE_KEY = "countryCode";
    public static final String NUMBER_KEY = "number";

    // We assume that phone number is required, it's guaranteed to be not null.
    public static Map<String, String> parsePhoneNumber(String phoneNumber) {
        Map<String, String> parsedData = new HashMap<>();
        String[] parsedNumber = phoneNumber.split(" ");
        String countryCode = "+" + parsedNumber[0].replace("(", "").replace(")", "");

        parsedData.put(COUNTRY_CODE_KEY, countryCode);
        parsedData.put(NUMBER_KEY, parsedNumber[1]);

        return parsedData;
    }
}
