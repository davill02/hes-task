package com.hes.aartuhanau.demotask.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hes.aartuhanau.demotask.entities.Customer;

public interface CustomerService {

    Customer getCustomerByName(String name) throws UsernameNotFoundException;

    Customer createCustomer(String name, String passwordEncrypted);
}
