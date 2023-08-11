package com.poly.service.lmpl;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicelmpl implements ProductService {
    @Autowired
    ProductDAO pdao;

    @Override
    public List<Product> findAll(){
        return pdao.findAll();
    }
    @Override
    public Product findByID(Integer id){
        return pdao.findById(id).get();
    }

    public List<Product> findbyCategoryId(String cid, Pageable pageable) {
        return pdao.findByCategoryId(cid, pageable);
    }

    @Override
    public List<Product> findByPriceAndCategoryId(String categoryId, Double minPrice, Double maxPrice) {
        return pdao.findByPriceAndCategoryId(categoryId, minPrice, maxPrice);
    }
    @Override
    public List<Product> findPaginated(Pageable pageable) {
        Page<Product> productPage = pdao.findAll(pageable);
        return productPage.getContent();
    }

    @Override
    public long getTotalProducts() {
        return pdao.count();
    }
    @Override
    public List<Product> searchByKeyword(String keyword, Pageable pageable) {
        return pdao.searchByKeyword(keyword, pageable);
    }
    @Override
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
        return pdao.findByPriceBetween(minPrice, maxPrice, pageable);
    }
}
