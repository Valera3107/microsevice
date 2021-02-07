package com.ua.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class BillRequestDTO {

    private Long accountId;

    private Boolean isDefault;

    private Boolean overdraftEnabled;

    private BigDecimal amount;

    private OffsetDateTime creationDate;
}
