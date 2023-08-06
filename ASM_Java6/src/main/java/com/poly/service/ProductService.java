package com.poly.service;

import com.poly.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    List<Product> findProductsByPage(int pageNumber, int pageSize);



    Product findByID(Integer id);

    List<Product> findbyCategoryId(String cid);
}
