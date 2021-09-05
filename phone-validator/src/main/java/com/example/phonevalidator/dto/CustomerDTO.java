package com.example.phonevalidator.dto;

import com.example.phonevalidator.common.Dictionary;
import com.example.phonevalidator.common.PhoneNumberParser;
import com.example.phonevalidator.common.PhoneNumberValidator;
import com.example.phonevalidator.entity.Customer;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Map;

@With
@Value
@Builder
public class CustomerDTO {
    Integer id;

    String name;

    String countryCode;

    String country;

    String phone;

    Boolean isValid;

    public static CustomerDTO from(Customer customer) {
        Map<String, String> parsedData = PhoneNumberParser.parsePhoneNumber(customer.getPhone());
        String countryCode = parsedData.get(PhoneNumberParser.COUNTRY_CODE_KEY);
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .country(Dictionary.CONFIG.get(countryCode).get(Dictionary.COUNTRY_NAME_KEY))
                .countryCode(countryCode)
                .phone(parsedData.get(PhoneNumberParser.NUMBER_KEY))
                .isValid(PhoneNumberValidator.isValid(parsedData.get(PhoneNumberParser.COUNTRY_CODE_KEY), customer.getPhone()))
                .build();
    }
}
