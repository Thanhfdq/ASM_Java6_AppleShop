package com.poly.controller.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/admin/index")
    public String Dashboard(Model model) {
        return "admin/index";
    }

    @RequestMapping("/admin/product")

    public String Product(Model model) {
        return "admin/products";
    }

    @RequestMapping("/admin/account")

    public String Account(Model model) {
        return "admin/accounts";
    }

    @RequestMapping("/admin/order")

    public String Order(Model model) {
        return "admin/orders";
    }

}
