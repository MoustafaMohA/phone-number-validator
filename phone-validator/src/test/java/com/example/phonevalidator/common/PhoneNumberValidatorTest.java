package com.example.phonevalidator.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneNumberValidatorTest {

    @Test
    public void validPhoneNumberTest() {
        Assertions.assertTrue(PhoneNumberValidator.isValid("+256", "(256) 775069443"));
    }

    @Test
    public void validPhoneNumber2Test() {
        Assertions.assertTrue(PhoneNumberValidator.isValid("+258", "(258) 846565883"));
    }

    @Test
    public void invalidPhoneNumberTest() {
        Assertions.assertFalse(PhoneNumberValidator.isValid("+256", "(256) 7771031454"));
    }
}
