package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/index")
    public String test(){
        return "user/index";
    }

    @RequestMapping("/product")
    public String testv2(){
        return "user/product";
    }
    @RequestMapping("/productv2")
    public String testv3(){
        return "user/about";
    }
}
