package com.example.demo.web;

import com.example.demo.item.Item;
import com.example.demo.item.Item2;
import com.example.demo.service.ProductService;
import com.example.demo.tradelog.TradeLog3;
import com.example.demo.user.SiteUser;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductResource {
    private final ProductService productService;
    public ProductResource(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value="/api/products")
    public ResponseEntity<List<Item>> findAllProducts(){
        List<Item> items = productService.findAll();
        return ResponseEntity.ok(items);
    }
    @GetMapping(value="/api/products/{name}")
    public ResponseEntity<Item> findByName(@PathVariable(value="name") String name){
        Item item = productService.findByName(name);
        return ResponseEntity.ok(item);
    }
    @GetMapping(value="/api/products/test/{name}")
    public String findByName2(@PathVariable(value="name") String name, Model model){
        List<TradeLog3> items = productService.findByName2(name);
        model.addAttribute("items", items);

        return "";
    }



}
