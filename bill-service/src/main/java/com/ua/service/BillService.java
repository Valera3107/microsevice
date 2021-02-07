package com.ua.service;

import com.ua.entity.Bill;
import com.ua.exception.BillNotFoundException;
import com.ua.repo.BillRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repository;

    public List<Bill> getBillsByAccountId(Long accountId) {
        return repository.getBillsByAccountId(accountId);
    }

    public Bill getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BillNotFoundException("Unable to find bill with id: " + id));
    }

    public Long create(Long accountId, Boolean isDefault, Boolean overdraftEnabled, BigDecimal amount) {

        val bill = Bill.builder()
                .accountId(accountId)
                .amount(amount)
                .creationDate(OffsetDateTime.now())
                .isDefault(isDefault)
                .overdraftEnabled(overdraftEnabled)
                .build();

        return repository.save(bill).getBillId();
    }

    public Bill update(Long id, Long accountId, Boolean isDefault, Boolean overdraftEnabled, BigDecimal amount) {
        val bill = Bill.builder()
                .billId(id)
                .accountId(accountId)
                .amount(amount)
                .creationDate(OffsetDateTime.now())
                .isDefault(isDefault)
                .overdraftEnabled(overdraftEnabled)
                .build();

        return repository.save(bill);
    }

    public Bill delete(Long id) {
        val bill = getById(id);
        repository.deleteById(id);
        return bill;
    }
}
