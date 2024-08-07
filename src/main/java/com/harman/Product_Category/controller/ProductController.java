package com.harman.Product_Category.controller;

import com.harman.Product_Category.model.Category;
import com.harman.Product_Category.model.Product;
import com.harman.Product_Category.service.CategoryService;
import com.harman.Product_Category.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

//    @GetMapping("/test")
//    public String testing() {
//        return "testing......";
//    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        Product product=productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product){
        Product oldProduct=productService.findById(id);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setCategory(product.getCategory());
        Product updateProduct=productService.save(product);
        return ResponseEntity.ok(updateProduct).getBody();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category){
        return categoryService.findByName(category);`
    }

    @GetMapping("/groupByCategory")
    public Map<Category, List<Product>> getProductByGroupByCategory(){
        return productService.getProductGroupedByCategory();
    }
}
