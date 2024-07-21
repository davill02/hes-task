package com.hes.aartuhanau.demotask.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hes.aartuhanau.demotask.daos.CustomerDao;
import com.hes.aartuhanau.demotask.entities.Customer;
import com.hes.aartuhanau.demotask.services.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class DefaultCustomerService implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer getCustomerByName(String name) throws UsernameNotFoundException {
        return customerDao.findCustomerByName(name).orElseThrow(() -> new UsernameNotFoundException(name));
    }

    @Override
    @Transactional
    public Customer createCustomer(String name, String passwordEncrypted) {
        if (customerDao.findCustomerByName(name).isPresent()) {
            throw new DuplicateKeyException("Not unique name");
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPassword(passwordEncrypted);
        return customerDao.save(customer);
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

}
