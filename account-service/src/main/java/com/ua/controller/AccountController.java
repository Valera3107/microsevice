package com.ua.controller;

import com.ua.controller.dto.AccountRequestDTO;
import com.ua.controller.dto.AccountResponseDTO;
import com.ua.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping("/{accountId}")
    public AccountResponseDTO getAccount(@PathVariable("accountId") Long id) {
        return new AccountResponseDTO(service.getAccountById(id));
    }

    @PostMapping("/")
    public Long createAccount(@RequestBody AccountRequestDTO request){
        return service.createAccount(request.getName(), request.getEmail(), request.getPhone(), request.getBills());
    }

    @PutMapping("/{accountId}")
    public AccountResponseDTO updateAccount(@PathVariable("accountId") Long id, @RequestBody AccountRequestDTO request){
        return new AccountResponseDTO(service.updateAccount(id, request.getName(), request.getEmail(), request.getPhone(), request.getBills()));
    }

    @DeleteMapping("/{accountId}")
    public AccountResponseDTO deleteAccount(@PathVariable("accountId") Long id) {
        return new AccountResponseDTO(service.deleteAccount(id));
    }
}
