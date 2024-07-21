package com.hes.aartuhanau.demotask.controllers.dto;

public interface Converter<S, T> {

    S convert(T t);

}
