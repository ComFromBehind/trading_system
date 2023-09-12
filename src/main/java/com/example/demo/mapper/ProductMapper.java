package com.example.demo.mapper;

import com.example.demo.item.Item;
import com.example.demo.item.Item2;
import com.example.demo.tradelog.TradeLog;
import com.example.demo.tradelog.TradeLog2;
import com.example.demo.tradelog.TradeLog3;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    List<Item> findAll();

    Item findByName(String name);

    List<TradeLog3> findByName2(String name);

    List<TradeLog3> findByName3(String name);

    List<TradeLog3> findByName23(String name);
    List<TradeLog> itemListfindByLoginId(String name);

    List<TradeLog2> tradeListfindByLoginId(String name, String product);


}
