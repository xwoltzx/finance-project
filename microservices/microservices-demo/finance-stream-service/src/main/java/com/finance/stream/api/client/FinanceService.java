package com.finance.stream.api.client;

import com.finance.stream.api.dto.FinanceApiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "finance-stream-service")
public interface FinanceService {

    @GetMapping
    List<FinanceApiDTO> getMOEXInformation(@RequestParam String index);
}
