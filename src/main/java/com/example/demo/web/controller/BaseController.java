package com.example.demo.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

@Validated
public abstract class BaseController<Dto, Entity> {

    @Autowired
    protected ModelMapper modelMapper;

    protected abstract Dto convertToDto(Entity entity);

    protected abstract Entity convertToEntity(Dto dto);

}
