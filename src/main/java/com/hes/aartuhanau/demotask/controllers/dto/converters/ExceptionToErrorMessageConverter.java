package com.hes.aartuhanau.demotask.controllers.dto.converters;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hes.aartuhanau.demotask.controllers.dto.Converter;

@Component
public class ExceptionToErrorMessageConverter implements Converter<String, Exception> {
    private final Map<Class<?>, String> classToErrorMessage;

    public ExceptionToErrorMessageConverter() {
        this.classToErrorMessage = new HashMap<>();
    }

    @Override
    public String convert(Exception exception) {
        String errorMsg = classToErrorMessage.get(exception.getClass());
        return errorMsg == null ? exception.getMessage() : errorMsg;
    }

}
