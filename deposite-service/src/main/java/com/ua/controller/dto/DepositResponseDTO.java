package com.ua.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DepositResponseDTO {

    private BigDecimal amount;

    private String email;
}
