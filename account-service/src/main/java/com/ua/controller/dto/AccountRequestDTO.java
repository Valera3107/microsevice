package com.ua.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AccountRequestDTO {

    private String name;

    private String email;

    private String phone;

    private List<Long> bills;
}
