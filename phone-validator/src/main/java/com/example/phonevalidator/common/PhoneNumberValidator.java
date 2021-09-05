package com.example.phonevalidator.common;

import lombok.NonNull;

/**
 * If there is another type of validation to implement, we can add a new interface with method isValid and implement this interface.
 */
public class PhoneNumberValidator {
    public static boolean isValid(@NonNull String countryCode, @NonNull String phoneNumber) {
        String pattern = Dictionary.CONFIG.get(countryCode).get(Dictionary.VALIDATOR_KEY);
        return phoneNumber.matches(pattern);
    }
}
