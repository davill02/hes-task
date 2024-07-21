package com.hes.aartuhanau.demotask.controllers.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hes.aartuhanau.demotask.entities.BankAccount;
import com.hes.aartuhanau.demotask.services.BankAccountService;

@Component
public class AccessBankAccountValidator implements AccessValidator {
    @Autowired
    private BankAccountService bankAccountService;

    public boolean validate(Long bankAccountId, String username) {
        BankAccount bankAccount = bankAccountService.getBankAccountById(bankAccountId);
        if (bankAccount == null || bankAccount.getCustomer() == null || username == null) {
            return false;
        } else {
            return username.equals(bankAccount.getCustomer().getName());
        }
    }

}
