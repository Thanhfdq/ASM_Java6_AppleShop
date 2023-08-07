package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.service.UserService;



@Controller
public class TestController {
	@Autowired
	UserService userService;
	
	//test commit >Z?????????
    @RequestMapping("/user/wellcome")
    public String test(){
        return "/admin/login.html";
    }
    
    @RequestMapping("/auth/login")
    public String testv2(){
        return "/admin/login.html";
    }
    
    @RequestMapping("/payment")
    public String testv2(Model model){
        model.addAttribute("message", "payment");
    	return "/user/index.html";
    }
}
