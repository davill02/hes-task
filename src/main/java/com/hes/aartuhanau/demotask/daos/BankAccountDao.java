package com.hes.aartuhanau.demotask.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.hes.aartuhanau.demotask.entities.BankAccount;

import jakarta.persistence.LockModeType;

import java.util.Optional;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<BankAccount> findByIsBlockedAndId(Boolean isBlocked, Long id);
}
