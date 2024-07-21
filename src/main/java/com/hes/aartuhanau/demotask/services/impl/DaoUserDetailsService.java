package com.hes.aartuhanau.demotask.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hes.aartuhanau.demotask.entities.Administrator;
import com.hes.aartuhanau.demotask.entities.Customer;
import com.hes.aartuhanau.demotask.services.CustomerService;

@Service
public class DaoUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Customer customer = customerService.getCustomerByName(username);
        return new User(username, customer.getPassword(), getGrantedAuthorities(customer));
    }

    private static List<GrantedAuthority> getGrantedAuthorities(Customer customer) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(2);
        if (customer instanceof Administrator) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthorities;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

}
