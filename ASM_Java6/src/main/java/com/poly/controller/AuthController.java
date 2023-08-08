package com.poly.controller;

import java.util.stream.Collectors;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Account;
import com.poly.service.UserService;

@Controller
public class AuthController {
	@Autowired
	UserService userService;
	
	
	@GetMapping(value = "/auth/login/form")
	public String loginForm() {
		return "/admin/login.html";
	}
	
	@RequestMapping("/auth/login/success")
    public String AuthLogin(Model model, @RequestParam("email") String username) {
    	model.addAttribute("message","Success");
    	System.out.println(username);
    	String url = "";
    	Account account = userService.getOneByUsername(username);
    	String[] roles = account.getAuthorities().stream()
				.map(authority -> authority.getRole().getId())
				.collect(Collectors.toList()).toArray(new String[0]);
    	System.out.println(roles);
    	if(roles.equals("admin")) {
    		url = "/admin/index"; 
    	} else {
    		url = "/product/list";
    	}
    	return "redirect:"+ url;
    }
    
    @RequestMapping("/oauth2/login/success")
    public String OAuthLogin(OAuth2AuthenticationToken oauth2,Model model) {
    	userService.loginFormOAuth2(oauth2);
    	model.addAttribute("message","Success");
    	return "redirect:/product/list";
    }
    
    @RequestMapping("/auth/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "forward:/auth/login/form";
	}
	
	@RequestMapping("/auth/logoff/success")
	public String logoff(Model model) {

		model.addAttribute("message", "Đăng xuất thành công!");
		return "forward:/auth/login/form";
	}
	
	@RequestMapping("/auth/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy xuất!");
		return "forward:/auth/login/form";
	}

}
