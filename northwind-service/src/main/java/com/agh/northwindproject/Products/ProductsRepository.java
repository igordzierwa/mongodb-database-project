package com.agh.northwindproject.Products;

import com.agh.northwindproject.Categories.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends MongoRepository<Product, String> {
    List<Product> findAll();
    Product findByProductName(String productName);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllBySupplierID(String supplierID);
}
