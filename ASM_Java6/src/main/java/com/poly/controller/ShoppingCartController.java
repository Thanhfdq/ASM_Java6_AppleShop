package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {
	
	@RequestMapping("/cart/view")
	public String view() {
		return "user/shoping-cart";
	}
	
}
