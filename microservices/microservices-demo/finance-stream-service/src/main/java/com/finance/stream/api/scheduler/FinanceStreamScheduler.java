package com.finance.stream.api.scheduler;

import com.finance.stream.api.client.FinanceService;
import com.finance.stream.api.service.ProcessData;
import com.microservices.demo.config.data.FinanceDataStreamConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinanceStreamScheduler {
    private final FinanceService financeService;
    private final FinanceDataStreamConfig financeDataStreamConfig;
    private final ProcessData processData;

    @Scheduled(fixedDelay = 20000)
    public void getMOEXInformation() {
        financeDataStreamConfig
                .getShareList()
                .forEach(moex ->
                {
                    var endeksUrl = moex.concat(financeDataStreamConfig.getUrlAppend());
                    var moexShare = financeService.getMOEXInformation(endeksUrl);

                    if (moexShare.isEmpty()){
                        log.error("Error on getting information");
                        throw new RuntimeException("Error on getting information");
                    }

                    try {
                        processData.processOnData(moexShare);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        log.error("Error in interrupted or processing data with kafka: {}",  e.getMessage());
                    }
                });

    }

}

