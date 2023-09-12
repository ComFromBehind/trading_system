package com.example.demo.service;

import com.example.demo.item.Item;
import com.example.demo.item.Item2;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.tradelog.TradeLog;
import com.example.demo.tradelog.TradeLog2;
import com.example.demo.tradelog.TradeLog3;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

    public List<Item> findAll(){
        return productMapper.findAll();
    }
    public Item findByName(String name){
        return productMapper.findByName(name);
    }

    public List<TradeLog3> findByName2(String name){

        return productMapper.findByName2(name);
    }
    public List<TradeLog3> findByName3(String name){
        return productMapper.findByName3(name);
    }

    public List<TradeLog3> findByName23(String name){return productMapper.findByName23(name);}
    public List<TradeLog> itemListfindByLoginId(String name){
        return productMapper.itemListfindByLoginId(name);
    }

    public List<TradeLog2> tradeListfindByLoginId(String name, String product){
        return productMapper.tradeListfindByLoginId(name , product);
    }



}
