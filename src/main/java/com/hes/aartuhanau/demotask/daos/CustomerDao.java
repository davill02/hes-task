package com.hes.aartuhanau.demotask.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hes.aartuhanau.demotask.entities.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByName(String name);
}
