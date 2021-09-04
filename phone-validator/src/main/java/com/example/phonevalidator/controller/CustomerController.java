package com.example.phonevalidator.controller;

import com.example.phonevalidator.dto.CustomerDTO;
import com.example.phonevalidator.service.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    List<CustomerDTO> getPageOfCustomers(Pageable pageable) {
        return customerService.getPageOfCustomers(pageable);
    }

    @GetMapping("/count")
    long count() {
        return customerService.count();
    }

    @PostMapping("/search")
    List<CustomerDTO> search(@RequestBody CustomerDTO customerDTO, Pageable pageable) {
        return customerService.search(customerDTO, pageable);
    }

    @PostMapping("count-search")
    long countSearch(@RequestBody CustomerDTO customerDTO) {
        return customerService.countSearch(customerDTO);
    }

}
