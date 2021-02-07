package com.ua.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ua.controller.dto.DepositRequestDTO;
import com.ua.controller.dto.DepositResponseDTO;
import com.ua.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepositController {

    private final DepositService service;

    @PostMapping("/deposits")
    public DepositResponseDTO deposit(@RequestBody DepositRequestDTO request) throws JsonProcessingException {
        return service.deposit(request.getAccountId(), request.getBillId(), request.getAmount());
    }
}
