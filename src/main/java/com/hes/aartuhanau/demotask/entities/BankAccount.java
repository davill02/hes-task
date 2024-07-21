package com.hes.aartuhanau.demotask.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    private Long id;
    private Long amount;
    private Boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
