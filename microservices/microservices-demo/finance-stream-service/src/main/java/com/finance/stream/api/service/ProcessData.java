package com.finance.stream.api.service;

import com.finance.stream.api.dto.FinanceApiDTO;

import java.util.List;

public interface ProcessData {
    void processOnData(List<FinanceApiDTO> financeApiDTOs);
}
