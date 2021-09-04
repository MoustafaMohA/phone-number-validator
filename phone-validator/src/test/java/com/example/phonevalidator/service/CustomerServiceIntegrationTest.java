package com.example.phonevalidator.service;

import com.example.phonevalidator.dto.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
public class CustomerServiceIntegrationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void searchByTelephoneNumberTest() {
        List<CustomerDTO> customerDTOList = customerService.search(CustomerDTO.builder().countryCode("+237").build(), PageRequest.of(0, 30));
        Assertions.assertNotNull(customerDTOList);
    }

    @Test
    public void searchByValidTelephoneNumberTest() {
        List<CustomerDTO> customerDTOList = customerService.search(CustomerDTO.builder().isValid(true).build(), PageRequest.of(0, 30));
        Assertions.assertNotNull(customerDTOList);
    }

    @Test
    public void searchByValidTelephoneNumberAndCountryCodeTest() {
        List<CustomerDTO> customerDTOList = customerService.search(CustomerDTO.builder().isValid(true).countryCode("+237").build(), PageRequest.of(0, 30));
        Assertions.assertNotNull(customerDTOList);
    }

    @Test
    public void searchAndCountTest() {
        long count  = customerService.countSearch(CustomerDTO.builder().isValid(true).countryCode("+237").build());
        Assertions.assertTrue(count > 0);
    }
}
