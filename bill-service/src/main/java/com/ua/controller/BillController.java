package com.ua.controller;

import com.ua.controller.dto.BillRequestDTO;
import com.ua.controller.dto.BillResponseDTO;
import com.ua.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping("/{id}")
    public BillResponseDTO getById(@PathVariable Long id) {
        return new BillResponseDTO(service.getById(id));
    }

    @GetMapping("/account/{id}")
    public List<BillResponseDTO> getByAccountId(@PathVariable Long id) {
        return service.getBillsByAccountId(id).stream()
                .map(BillResponseDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public Long create(@RequestBody BillRequestDTO request) {
        return service.create(request.getAccountId(), request.getIsDefault(), request.getOverdraftEnabled(), request.getAmount());
    }

    @PutMapping("/{id}")
    public BillResponseDTO update(@PathVariable Long id, @RequestBody BillRequestDTO request) {
        return new BillResponseDTO(service.update(id, request.getAccountId(), request.getIsDefault(), request.getOverdraftEnabled(), request.getAmount()));
    }

    @DeleteMapping("/{id}")
    public BillResponseDTO delete(@PathVariable Long id) {
        return new BillResponseDTO(service.delete(id));
    }
}
