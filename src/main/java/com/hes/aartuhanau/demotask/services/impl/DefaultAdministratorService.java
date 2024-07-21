package com.hes.aartuhanau.demotask.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hes.aartuhanau.demotask.daos.BankAccountDao;
import com.hes.aartuhanau.demotask.services.AdministartorService;

@Service
public class DefaultAdministratorService implements AdministartorService {

    @Autowired
    private BankAccountDao bankAccountDao;

    private void setBankAccountStatus(Long id, boolean isBlocked) {
        bankAccountDao.findByIsBlockedAndId(!isBlocked, id).ifPresent(acc -> {
            acc.setIsBlocked(isBlocked);
            bankAccountDao.save(acc);
        });

    }

    @Override
    @Transactional
    public void blockBankAccount(Long id) {
        setBankAccountStatus(id, true);
    }

    @Override
    @Transactional
    public void unblockBankAccount(Long id) {
        setBankAccountStatus(id, false);
    }

    public void setBankAccountDao(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

}
