package com.hes.aartuhanau.demotask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hes.aartuhanau.demotask.entities.BankAccount;
import com.hes.aartuhanau.demotask.services.AdministartorService;
import com.hes.aartuhanau.demotask.services.BankAccountService;

@Controller
@Secured("ROLE_ADMIN")
public class AdminstartorController {

    @Autowired
    private AdministartorService adminService;
    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/admin")
    public String getBankAccounts(Model model) {
        List<BankAccount> accounts = bankAccountService.getBankAccounts();
        model.addAttribute("accounts", accounts);
        return "admin";
    }

    @PostMapping("/admin/block/{accountId}")
    public String blockBankAccount(@PathVariable Long accountId) {
        adminService.blockBankAccount(accountId);
        return "redirect:/admin";
    }

    @PostMapping("/admin/unblock/{accountId}")
    public String unblockBankAccount(@PathVariable Long accountId) {
        adminService.unblockBankAccount(accountId);
        return "redirect:/admin";
    }

}
