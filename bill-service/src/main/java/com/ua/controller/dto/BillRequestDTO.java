package com.ua.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BillRequestDTO {

    private Long accountId;

    private Boolean isDefault;

    private Boolean overdraftEnabled;

    private BigDecimal amount;
}
