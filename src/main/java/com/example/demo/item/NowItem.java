package com.example.demo.item;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;
import com.example.demo.tradelog.TradeLog3;

import java.util.List;

public class NowItem {
    private static String nowItem;
    private static Integer nowPrice;
    public static String getNowItem() {
        return nowItem;
    }
    public static Integer getNowPrice(){

        return nowPrice;
    }

    public static void setNowPrice(Integer nowPrice) {
        NowItem.nowPrice = nowPrice;
    }

    public static void setNowItem(String nowItem) {
        NowItem.nowItem = nowItem;
    }
}
