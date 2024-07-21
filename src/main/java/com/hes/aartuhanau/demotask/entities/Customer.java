package com.hes.aartuhanau.demotask.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<BankAccount> accounts;
    private String password;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<BankAccount> getAccounts() {
        return accounts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
