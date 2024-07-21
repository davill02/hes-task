package com.hes.aartuhanau.demotask.services.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hes.aartuhanau.demotask.daos.CustomerDao;
import com.hes.aartuhanau.demotask.entities.Customer;

public class DefaultCustomerServiceTest {
    private static CustomerDao customerDao;
    private static DefaultCustomerService defaultCustomerService = new DefaultCustomerService();

    @BeforeAll
    static void init() {
        customerDao = mock(CustomerDao.class);
        defaultCustomerService.setCustomerDao(customerDao);
    }

    @Test
    void testGetCustomerByNameWithoutException() {
        when(customerDao.findCustomerByName(any())).thenReturn(Optional.of(new Customer()));

        Customer customer = defaultCustomerService.getCustomerByName("name");

        assertNotNull(customer);
    }

    @Test
    void testGetCustomerByNameNotFound() {
        when(customerDao.findCustomerByName(any())).thenReturn(Optional.empty());

        assertThrowsExactly(UsernameNotFoundException.class, () -> defaultCustomerService.getCustomerByName("name"));
    }

    @Test
    void testCreateCustomer() {
        when(customerDao.findCustomerByName(any())).thenReturn(Optional.empty());
        when(customerDao.save(any())).thenReturn(new Customer());

        Customer customer = defaultCustomerService.createCustomer("name", "password");

        assertNotNull(customer);
    }

    @Test
    void testCreateCustomerNotUniqueName() {
        when(customerDao.findCustomerByName(any())).thenReturn(Optional.of(new Customer()));

        assertThrowsExactly(DuplicateKeyException.class,
                () -> defaultCustomerService.createCustomer("name", "password"));

    }
}
