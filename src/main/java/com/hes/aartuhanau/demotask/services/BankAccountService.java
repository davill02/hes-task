package com.hes.aartuhanau.demotask.services;

import java.util.List;

import com.hes.aartuhanau.demotask.entities.BankAccount;
import com.hes.aartuhanau.demotask.entities.Customer;

public interface BankAccountService {
    Long withdrawAmount(Long amount, Long account);

    Long addAmount(Long amount, Long account);

    BankAccount createBankAccountForCustomer(Customer customer);

    List<BankAccount> getBankAccounts();

    BankAccount getBankAccountById(Long id);
}
