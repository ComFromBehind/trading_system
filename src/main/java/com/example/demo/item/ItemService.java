package com.example.demo.item;


import com.example.demo.tradelog.TradeLog;
import com.example.demo.tradelog.TradeLog2;
import com.example.demo.tradelog.TradeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ItemService {
    private final ItemRepository itemRepository;
    private final TradeLogRepository tradeLogRepository;

    public Item create(String name,Integer st, Integer hm){
        Item item = new Item();
        item.setName(name);
        item.setPrice(st);
        item.setHowmany(hm);
        this.itemRepository.save(item);
        return item;
    }


}
