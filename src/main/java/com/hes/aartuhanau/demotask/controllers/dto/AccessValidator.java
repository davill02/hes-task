package com.hes.aartuhanau.demotask.controllers.dto;

public interface AccessValidator {
    public boolean validate(Long bankAccountId, String username);
}
