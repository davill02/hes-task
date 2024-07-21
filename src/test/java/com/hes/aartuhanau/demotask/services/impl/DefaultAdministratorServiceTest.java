package com.hes.aartuhanau.demotask.services.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.hes.aartuhanau.demotask.daos.BankAccountDao;
import com.hes.aartuhanau.demotask.entities.BankAccount;

public class DefaultAdministratorServiceTest {

    private static DefaultAdministratorService administratorService;
    private static BankAccountDao bankAccountDao;
    private static BankAccount bankAccount = new BankAccount();

    @BeforeAll
    static void init() {
        administratorService = new DefaultAdministratorService();
        bankAccountDao = mock(BankAccountDao.class);
        administratorService.setBankAccountDao(bankAccountDao);
    }

    @Test
    void testBlockBankAccount() {
        bankAccount.setIsBlocked(false);
        when(bankAccountDao.findByIsBlockedAndId(any(), any())).thenReturn(Optional.of(bankAccount));

        administratorService.blockBankAccount(1L);

        assertTrue(bankAccount::getIsBlocked);
    }

    @Test
    void testUnblockBankAccount() {
        bankAccount.setIsBlocked(true);
        when(bankAccountDao.findByIsBlockedAndId(any(), any())).thenReturn(Optional.of(bankAccount));

        administratorService.unblockBankAccount(1L);

        assertFalse(bankAccount::getIsBlocked);
    }
}
