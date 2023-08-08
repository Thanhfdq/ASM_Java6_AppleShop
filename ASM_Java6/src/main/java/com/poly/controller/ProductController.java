package com.poly.controller;

import com.poly.entity.Product;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
@Autowired
    ProductService productService;
    private final int pageSize = 8;
    @GetMapping("/user/home")
    public String list(@RequestParam(defaultValue = "0") int page,Model model, @RequestParam("cid") Optional<String> cid){
        if (cid.isPresent()){
            List<Product> list = productService.findbyCategoryId(cid.get());

            return "user/product";
        }else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
            List<Product> list = productService.findPaginated(pageable);

            int totalPages = (int) Math.ceil((double) productService.getTotalProducts() / pageSize);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("items", list);
            return "user/index";
        }


    }

    @RequestMapping("user/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Product item = productService.findByID(id);
        model.addAttribute("item", item);
        List<Product> list = productService.findAll();
        model.addAttribute("items", list);

        return "user/product-detail";
    }
    
    //Shop
    @RequestMapping("/user/shop")
    public String listShop(Model model, @RequestParam("cid") Optional<String> cid){
        if (cid.isPresent()){
            List<Product> list = productService.findbyCategoryId(cid.get());
            model.addAttribute("items", list);
        }else {
            List<Product> list = productService.findAll();
            model.addAttribute("items", list);
        }

        return "user/product";
    }
}
