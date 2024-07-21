package com.hes.aartuhanau.demotask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hes.aartuhanau.demotask.controllers.dto.LoginForm;
import com.hes.aartuhanau.demotask.services.CustomerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/sign-up")
    public String singUp(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "registration";
    }

    @PostMapping("/sign-up/create-user")
    public String createUser(@ModelAttribute LoginForm loginForm, HttpServletRequest request) {
        try {
            customerService.createCustomer(loginForm.getName(), passwordEncoder.encode(loginForm.getPassword()));
        } catch (DuplicateKeyException notUnique) {
            return "redirect:/sign-up?error=" + notUnique.getMessage();
        }
        return "redirect:/";
    }
}
