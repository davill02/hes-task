package com.hes.aartuhanau.demotask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hes.aartuhanau.demotask.controllers.dto.Converter;
import com.hes.aartuhanau.demotask.controllers.dto.UserInfo;

@Controller
public class UserController {
    @Autowired
    private Converter<UserInfo, Authentication> principalToUserInfoConverter;

    @GetMapping("/")
    public String myProfile(Authentication authentication, Model model) {
        model.addAttribute("userInfo", principalToUserInfoConverter.convert(authentication));
        return "profile";
    }

}
