package com.example.phonevalidator.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PhoneNumberParserTest {

    @Test
    public void testParsingPhoneNumber() {
        Map<String, String> parsedData = PhoneNumberParser.parsePhoneNumber("(212) 6007989253");
        Assertions.assertEquals(parsedData.get(PhoneNumberParser.COUNTRY_CODE_KEY), "+212");
        Assertions.assertEquals(parsedData.get(PhoneNumberParser.NUMBER_KEY), "6007989253");
    }
}
