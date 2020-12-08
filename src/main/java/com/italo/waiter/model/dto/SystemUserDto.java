package com.italo.waiter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SystemUserDto {

    private String username;
    private String password;
    private List<RoleDto> roles = new ArrayList<>();

}
