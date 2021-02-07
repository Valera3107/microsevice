package com.ua.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ua.controller.dto.DepositResponseDTO;
import com.ua.entity.Deposit;
import com.ua.exception.DepositServiceException;
import com.ua.repository.DepositRepository;
import com.ua.rest.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DepositService {

    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";

    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final DepositRepository repository;

    private final AccountServiceClient accountServiceClient;

    private final BillServiceClient billServiceClient;

    private final RabbitTemplate template;

    public DepositResponseDTO deposit(Long accountId, Long billId, BigDecimal amount) throws JsonProcessingException {
        val isAccountIdExists = Objects.nonNull(accountId);
        val isBillIdExists = Objects.nonNull(billId);

        if (isAccountIdExists && isBillIdExists) {
            throw new DepositServiceException("Account and Bill are null");
        }

        val mapper = new ObjectMapper();

        if (isBillIdExists) {
            val billResponse = billServiceClient.getBillById(billId);
            val billRequest = createBillRequest(amount, billResponse);

            billServiceClient.update(billId, billRequest);

            val accountResponse = accountServiceClient.getAccountById(billResponse.getAccountId());

            saveDeposit(billId, amount, accountResponse);

            val depositResponse = DepositResponseDTO.builder()
                    .amount(amount)
                    .email(accountResponse.getEmail())
                    .build();

            template.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT, mapper.writeValueAsString(depositResponse));

            return depositResponse;
        }

        val defaultBill = getDefaultBill(accountId);
        val billRequest = createBillRequest(amount, defaultBill);
        billServiceClient.update(defaultBill.getBillId(), billRequest);

        val account = accountServiceClient.getAccountById(accountId);

        saveDeposit(billId, amount, account);

        val depositResponse = DepositResponseDTO.builder()
                .amount(amount)
                .email(account.getEmail())
                .build();

        template.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT, mapper.writeValueAsString(depositResponse));

        return depositResponse;
    }

    private void saveDeposit(Long billId, BigDecimal amount, AccountResponseDTO account) {
        repository.save(Deposit.builder()
                .amount(amount)
                .billId(billId)
                .creationDate(OffsetDateTime.now())
                .email(account.getEmail())
                .build());
    }

    private BillRequestDTO createBillRequest(BigDecimal amount, BillResponseDTO billResponse) {
        return BillRequestDTO.builder()
                .accountId(billResponse.getAccountId())
                .amount(billResponse.getAmount().add(amount))
                .creationDate(billResponse.getCreationDate())
                .isDefault(billResponse.getIsDefault())
                .overdraftEnabled(billResponse.getOverdraftEnabled())
                .build();
    }

    private BillResponseDTO getDefaultBill(Long id) {
        return billServiceClient.getBillsByAccountId(id).stream()
                .filter(BillResponseDTO::getIsDefault)
                .findFirst()
                .orElseThrow(() -> new DepositServiceException("Unable to find default bill with account id: " + id));
    }

}
