package com.hes.aartuhanau.demotask.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hes.aartuhanau.demotask.controllers.dto.AccessValidator;
import com.hes.aartuhanau.demotask.controllers.dto.Converter;
import com.hes.aartuhanau.demotask.entities.Customer;
import com.hes.aartuhanau.demotask.services.BankAccountService;
import com.hes.aartuhanau.demotask.services.CustomerService;

@Controller
public class BankAccountController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private AccessValidator accessValidator;
    @Autowired
    private Converter<String, Exception> exceptionToErrorMsg;

    @Secured("ROLE_USER")
    @GetMapping("/bank/accounts")
    public String getBankAccounts(Principal principal, Model model) {
        Customer customer = customerService.getCustomerByName(principal.getName());
        model.addAttribute("bankAccounts", customer.getAccounts());
        return "account";
    }

    @PostMapping("/create-bank-account")
    public String createBankAccount(Authentication authentication) {
        Customer customer = customerService.getCustomerByName(authentication.getName());
        bankAccountService.createBankAccountForCustomer(customer);
        return "redirect:/bank/accounts";
    }

    @PostMapping("/add/{bankAccountId}/{amount}")
    public String add(Authentication authentication, @PathVariable Long bankAccountId, @PathVariable Long amount,
            Model model) {
        String description = handleErrors(bankAccountId, amount, authentication.getName());
        return redirectIfSuccess(description);
    }

    private String handleErrors(Long bankAccountId, Long amount, String username) {
        String message = "";
        try {
            validateAccess(bankAccountId, amount, username);
        } catch (Exception e) {
            message = exceptionToErrorMsg.convert(e);
        }
        return message;
    }

    private void validateAccess(Long bankAccountId, Long amount, String username) throws IllegalAccessException {
        if (accessValidator.validate(bankAccountId, username)) {
            bankAccountService.addAmount(amount, bankAccountId);
        } else {
            throw new IllegalAccessException("Illegal Access to bank account");
        }
    }

    @PostMapping("/withdraw/{bankAccountId}/{amount}")
    public String withdraw(Authentication authentication, @PathVariable Long bankAccountId, @PathVariable Long amount,
            Model model) {
        String description = handleErrors(bankAccountId, -amount, authentication.getName());
        return redirectIfSuccess(description);
    }

    private String redirectIfSuccess(String errorString) {
        if (!errorString.isEmpty()) {
            return "redirect:/bank/accounts?error=" + errorString;
        }
        return "redirect:/bank/accounts";
    }

}
