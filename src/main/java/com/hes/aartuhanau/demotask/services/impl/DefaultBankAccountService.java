package com.hes.aartuhanau.demotask.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hes.aartuhanau.demotask.daos.BankAccountDao;
import com.hes.aartuhanau.demotask.entities.BankAccount;
import com.hes.aartuhanau.demotask.entities.Customer;
import com.hes.aartuhanau.demotask.services.BankAccountService;

import jakarta.transaction.Transactional;

@Service
public class DefaultBankAccountService implements BankAccountService {
    @Autowired
    private BankAccountDao bankAccountDao;

    @Override
    @Transactional
    public Long withdrawAmount(Long amount, Long bankAccount) {
        return add(-amount, bankAccount);
    }

    @Override
    @Transactional
    public Long addAmount(Long amount, Long bankAccount) {
        return add(amount, bankAccount);
    }

    private Long add(Long amount, Long bankAccountId) {
        BankAccount bankAccount = bankAccountDao.findByIsBlockedAndId(false, bankAccountId)
                .orElseThrow(() -> new IllegalStateException("Bank account is blocked"));

        if (amount + bankAccount.getAmount() < 0) {
            throw new IllegalArgumentException("Insufficient amount");
        }
        bankAccount.setAmount(bankAccount.getAmount() + amount);
        bankAccountDao.save(bankAccount);
        return bankAccount.getAmount();
    }

    @Override
    @Transactional
    public BankAccount createBankAccountForCustomer(Customer customer) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAmount(0L);
        bankAccount.setIsBlocked(false);
        bankAccount.setCustomer(customer);
        return bankAccountDao.save(bankAccount);
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return bankAccountDao.findAll();
    }

    @Override
    public BankAccount getBankAccountById(Long id) {
        return bankAccountDao.getReferenceById(id);
    }

    public void setBankAccountDao(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }
}
