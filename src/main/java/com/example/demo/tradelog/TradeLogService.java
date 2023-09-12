package com.example.demo.tradelog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TradeLogService {
    private final TradeLogRepository tradeLogRepository;

    public TradeLog create(String username, String itemname,
                           Integer buy, Integer sell, Integer howmany, Integer allsum){
        TradeLog tradeLog = new TradeLog();
        tradeLog.setUsername(username);
        tradeLog.setItemname(itemname);
        tradeLog.setBuy(buy);
        tradeLog.setSell(sell);
        tradeLog.setHowmany(howmany);
        tradeLog.setAllsum(allsum);
        this.tradeLogRepository.save(tradeLog);
        return tradeLog;
    }

}
