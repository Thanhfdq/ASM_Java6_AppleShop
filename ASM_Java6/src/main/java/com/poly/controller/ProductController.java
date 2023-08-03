package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProductController {
	
	@RequestMapping("/product/list")
    public String list(){
        return "user/product";
    }
	
	@RequestMapping("/product/detail{id}")
    public String detail(){
        return "user/product-detail";
    }
}
