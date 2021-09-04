package com.example.phonevalidator.common;

public class PhoneNumberValidator {
    public static boolean isValid(String countryCode, String phoneNumber) {
        String pattern = Dictionary.CONFIG.get(countryCode).get(Dictionary.VALIDATOR_KEY);
        return phoneNumber.matches(pattern);
    }
}
