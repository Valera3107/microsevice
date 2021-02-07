package com.ua.controller.dto;

import com.ua.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {

    private Long billId;

    private Long accountId;

    private Boolean isDefault;

    private OffsetDateTime creationDate;

    private Boolean overdraftEnabled;

    private BigDecimal amount;

    public BillResponseDTO(Bill bill) {
       this.billId = bill.getBillId();
       this.accountId = bill.getAccountId();
       this.isDefault = bill.getIsDefault();
       this.creationDate = bill.getCreationDate();
       this.overdraftEnabled = bill.getOverdraftEnabled();
       this.amount = bill.getAmount();
    }
}
