package com.ua.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "bill-service")
public interface BillServiceClient {

    @RequestMapping(value = "bills/{id}", method = RequestMethod.GET)
    BillResponseDTO getBillById(@PathVariable("id") Long billId);

    @RequestMapping(value = "bills/{id}", method = RequestMethod.PUT)
    void update(@PathVariable("id") Long billId, BillRequestDTO request);

    @RequestMapping(value = "bills/account/{id}", method = RequestMethod.GET)
    List<BillResponseDTO> getBillsByAccountId(@PathVariable("id") Long id);
}
