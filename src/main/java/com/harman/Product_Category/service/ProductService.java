package com.harman.Product_Category.service;

import com.harman.Product_Category.exception.ResourceNotFoundException;
import com.harman.Product_Category.model.Category;
import com.harman.Product_Category.model.Product;
import com.harman.Product_Category.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found"));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }


    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }


    public Map<Category, List<Product>> getProductGroupedByCategory() {
        return productRepository.findAll().stream()
                .collect(Collectors.groupingBy(Product::getCategory));
    }
}
