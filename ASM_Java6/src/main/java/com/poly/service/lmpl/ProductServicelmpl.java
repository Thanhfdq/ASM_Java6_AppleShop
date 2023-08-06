package com.poly.service.lmpl;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    @Override
    public List<Product> findbyCategoryId(String cid) {
        return pdao.findByCategoryId(cid);
    }
    @Override
    public List<Product> findProductsByPage(int pageNumber, int pageSize) {
        List<Product> allProducts = findAll();
        int totalProducts = allProducts.size();
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalProducts);
        if (startIndex >= totalProducts) {
            return Collections.emptyList();
        }
        return allProducts.subList(startIndex, endIndex);
    }
}
