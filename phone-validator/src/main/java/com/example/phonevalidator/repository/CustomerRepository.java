package com.example.phonevalidator.repository;

import com.example.phonevalidator.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>,
        QueryByExampleExecutor<Customer> {

    List<Customer> findAll();
}
