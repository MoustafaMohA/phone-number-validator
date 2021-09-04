package com.example.phonevalidator.repository;

import com.example.phonevalidator.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
public class CustomerRepoIntegrationTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testCustomerTableNotNull() {
        List<Customer> customers = customerRepository.findAll();
        Assertions.assertNotNull(customers);
    }

    @Test
    public void testCustomerTablePagination() {
        int pageIndex = 0;
        int pageSize = 10;
        Page<Customer> customerPage = customerRepository.findAll(PageRequest.of(pageIndex, pageSize));
        Assertions.assertEquals(customerPage.getNumber(), pageIndex);
        Assertions.assertEquals(customerPage.getSize(), pageSize);
    }
}
