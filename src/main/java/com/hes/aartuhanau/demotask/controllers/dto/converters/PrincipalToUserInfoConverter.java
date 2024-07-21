package com.hes.aartuhanau.demotask.controllers.dto.converters;

import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.hes.aartuhanau.demotask.controllers.dto.Converter;
import com.hes.aartuhanau.demotask.controllers.dto.UserInfo;

@Component
public class PrincipalToUserInfoConverter implements Converter<UserInfo, Authentication> {

    @Override
    public UserInfo convert(Authentication t) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(t.getName());
        userInfo.setRole(t.getAuthorities().stream().map(authority -> authority.getAuthority())
                .collect(Collectors.joining(" ")));
        return userInfo;
    }

}
