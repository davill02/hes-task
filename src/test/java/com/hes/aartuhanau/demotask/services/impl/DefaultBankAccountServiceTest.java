package com.hes.aartuhanau.demotask.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hes.aartuhanau.demotask.daos.BankAccountDao;
import com.hes.aartuhanau.demotask.entities.BankAccount;
import com.hes.aartuhanau.demotask.entities.Customer;

public class DefaultBankAccountServiceTest {

    private static DefaultBankAccountService defaultBankAccountService;
    private static BankAccountDao bankAccountDao;
    private static BankAccount bankAccount;

    @BeforeAll
    static void init() {
        defaultBankAccountService = new DefaultBankAccountService();
        bankAccountDao = mock(BankAccountDao.class);
        defaultBankAccountService.setBankAccountDao(bankAccountDao);
        bankAccount = new BankAccount();
    }

    @BeforeEach
    void reInit() {
        when(bankAccountDao.findByIsBlockedAndId(any(), any())).thenReturn(Optional.of(bankAccount));
    }

    @Test
    void testAddAmount() {
        bankAccount.setAmount(50L);
        bankAccount.setIsBlocked(false);

        defaultBankAccountService.addAmount(50L, 1L);

        assertEquals(bankAccount.getAmount(), 100L);
    }

    @Test
    void testCreateBankAccountForCustomer() {
        Customer customer = new Customer();
        customer.setName("name");
        when(bankAccountDao.save(any())).thenAnswer(ans -> ans.getArguments()[0]);

        BankAccount newBankAccount = defaultBankAccountService.createBankAccountForCustomer(customer);

        assertEquals(customer, newBankAccount.getCustomer());
    }

    @Test
    void testWithdrawAmount() {
        bankAccount.setAmount(50L);
        bankAccount.setIsBlocked(false);

        defaultBankAccountService.withdrawAmount(50L, 1L);

        assertEquals(bankAccount.getAmount(), 0L);

    }

    @Test
    void testWithdrawAmountIncorrectBalance() {
        bankAccount.setAmount(0L);
        bankAccount.setIsBlocked(false);

        assertThrows(IllegalArgumentException.class, () -> defaultBankAccountService.withdrawAmount(50L, 1L));
    }

    @Test
    void testWithdrawAmountToBlockedAccount() {
        when(bankAccountDao.findByIsBlockedAndId(any(), any())).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> defaultBankAccountService.withdrawAmount(50L, 1L));

    }
}
