package com.harman.Product_Category.repository;

import com.harman.Product_Category.model.Category;
import com.harman.Product_Category.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String category);
}
