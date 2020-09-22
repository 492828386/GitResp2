package com.example.demo.controller;


import com.example.demo.User;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    public HelloController(ProductService productService) {
        this.productService = productService;
    }

    private ProductService productService;

    @GetMapping("/test/{id}")
    @ResponseBody
    public String hello(@PathVariable("id") String id) {

        return productService.run(Integer.valueOf(id));
    }
    @GetMapping("/select")
    @ResponseBody
    public List<User> selectAllUser(){
        return productService.selectAllUser();
    }


}
