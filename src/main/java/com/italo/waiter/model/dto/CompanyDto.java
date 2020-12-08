package com.italo.waiter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CompanyDto {
    private String name;
    private List<SystemUserDto> systemUsers = new ArrayList<>();
}
