package com.example.phonevalidator.service;

import com.example.phonevalidator.common.Dictionary;
import com.example.phonevalidator.dto.CustomerDTO;
import com.example.phonevalidator.entity.Customer;
import com.example.phonevalidator.repository.CustomerRepository;
import com.example.phonevalidator.sql.SqlUtility;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final SqlUtility sqlUtility;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, SqlUtility sqlUtility) {
        this.customerRepository = customerRepository;
        this.sqlUtility = sqlUtility;
    }

    public List<CustomerDTO> getCustomerPage(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<Customer> customers = customerPage.getContent();
        return getCustomerDTOS(customers);
    }

    private List<CustomerDTO> getCustomerDTOS(List<Customer> customers) {
        return customers.stream().map(CustomerDTO::from).collect(Collectors.toList());
    }

    public long count() {
        return customerRepository.count();
    }

    @SneakyThrows
    public long countSearch(CustomerDTO customerDTO) {
        String search = buildSqlSearchClause(customerDTO);
        String sqlQuery = String.format("select count(*) as count from customer where phone %s", search);
        ResultSet rs = sqlUtility.getStatement().executeQuery(sqlQuery);
        int count = rs.getInt("count");
        rs.close();
        return count;
    }

    @SneakyThrows
    public List<CustomerDTO> search(CustomerDTO customerDTO, Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = limit * pageable.getPageNumber();
        String search = buildSqlSearchClause(customerDTO);
        String sqlStatement = String.format("select * from customer where phone %s LIMIT %d, %d", search, offset, limit);
        ResultSet rs = sqlUtility.getStatement().executeQuery(sqlStatement);
        List<Customer> customers = new ArrayList<>();
        while (rs.next()) {
            customers.add(Customer.builder().id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .phone(rs.getString("phone"))
                    .build());
        }
        rs.close();
        return getCustomerDTOS(customers);
    }

    private String buildSqlSearchClause(CustomerDTO customerDTO) {
        String pattern = "";
        String operator = "regexp";
        String countryCode = null;
        String countryFilter = "";
        if (customerDTO.getCountryCode() != null) {
            pattern = Dictionary.CONFIG.get(customerDTO.getCountryCode()).get(Dictionary.VALIDATOR_KEY).split(" ")[0];
            countryCode = customerDTO.getCountryCode();
        }

        if (customerDTO.getIsValid() != null) {
            Collection<Map<String, String>> data = countryCode != null ? List.of(Dictionary.CONFIG.get(countryCode)) : Dictionary.CONFIG.values();
            List<String> results = data.stream()
                    .map(stringStringMap -> stringStringMap.get(Dictionary.VALIDATOR_KEY))
                    .collect(Collectors.toList());
            pattern = String.join("|", results);
            if (!customerDTO.getIsValid()) {
                operator = "NOT regexp";

                if (countryCode != null) {
                    countryFilter = "AND phone like '%(" + countryCode.replace("+", "") + ")%'";
                }
            }
        }
        return String.format("%s '%s' %s", operator, pattern, countryFilter);
    }
}
