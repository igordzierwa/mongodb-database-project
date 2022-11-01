package com.agh.northwindproject.Categories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriesRepository extends MongoRepository<Category, String> {
    List<Category> findAll();
    Category findByCategoryName(String categoryName);
}
