package com.hes.aartuhanau.demotask.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import com.hes.aartuhanau.demotask.entities.Administrator;
import com.hes.aartuhanau.demotask.entities.Customer;
import com.hes.aartuhanau.demotask.services.CustomerService;

public class DaoUserDetailsServiceTest {

    private static DaoUserDetailsService service;
    private static CustomerService mockCustomerService;
    private static Customer customer;
    private static Administrator admin;

    @BeforeAll
    static void init() {
        service = new DaoUserDetailsService();
        mockCustomerService = mock(CustomerService.class);
        service.setCustomerService(mockCustomerService);
        customer = new Customer();
        customer.setName("user");
        customer.setPassword("any");
        admin = new Administrator();
        admin.setName("admin");
        admin.setPassword("user");
    }

    @Test
    void testLoadUserByUsernameAdmin() {
        when(mockCustomerService.getCustomerByName(any())).thenReturn(admin);

        UserDetails userDetails = service.loadUserByUsername("admin");

        assertEquals(userDetails.getAuthorities().size(), 2);
        assertEquals(userDetails.getUsername(), admin.getName());
    }

    @Test
    void testLoadUserByUsernameUser() {
        when(mockCustomerService.getCustomerByName(any())).thenReturn(customer);

        UserDetails userDetails = service.loadUserByUsername("user");

        assertEquals(userDetails.getAuthorities().size(), 1);
        assertEquals(userDetails.getUsername(), customer.getName());
    }
}
