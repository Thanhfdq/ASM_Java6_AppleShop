package com.poly.service;

import com.poly.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    List<Product> findPaginated(Pageable pageable);

    long getTotalProducts();

    Product findById(Integer id);

    List<Product> findByCategoryId(String cid);
    List<Product> findByPriceAndCategoryId(String categoryId, Double minPrice, Double maxPrice);
    
    public Product create(Product product) ;

	public Product update(Product product) ;

	public void delete(Integer id) ;
}
