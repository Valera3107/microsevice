package com.ua.service;

import com.ua.entity.Account;
import com.ua.exception.AccountNotFoundException;
import com.ua.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public Account getAccountById(Long id) {
        return repository.findById(id).orElseThrow(() -> new AccountNotFoundException("Unable to find account with id: " + id));
    }

    public Long createAccount(String name, String email, String phone, List<Long> bills) {
        val account = Account.builder()
                .bills(bills)
                .email(email)
                .name(name)
                .phone(phone)
                .creationDate(OffsetDateTime.now())
                .build();

        return repository.save(account).getAccountId();
    }

    public Account updateAccount(Long id, String name, String email, String phone, List<Long> bills) {
        val account = Account.builder()
                .accountId(id)
                .bills(bills)
                .email(email)
                .name(name)
                .phone(phone)
                .creationDate(OffsetDateTime.now())
                .build();

        return repository.save(account);
    }

    public Account deleteAccount(Long id) {
        val account = getAccountById(id);
        repository.deleteById(id);
        return account;
    }
}
