package com.agh.northwindproject.Categories;

import com.agh.northwindproject.Products.Product;
import com.agh.northwindproject.Products.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CategoriesController {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping(value = "/api/categories")
    @ResponseBody
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoriesRepository.findAll());
    }

    @PostMapping(value = "/api/category")
    @ResponseBody
    public ResponseEntity<String> addNewCategory(@RequestBody CategoryRequestBody categoryRequestBody){
        categoriesRepository.save(new Category(categoryRequestBody));
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @PutMapping(value = "/api/category/{categoryID}")
    @ResponseBody
    public ResponseEntity<String> updateCategory(@PathVariable String categoryID,
                                                 @RequestBody CategoryRequestBody categoryRequestBody) {
        if(categoriesRepository.findById(categoryID).orElse(null) != null) {
            Category category = new Category(categoryRequestBody);
            category.setId(categoryID);
            categoriesRepository.save(category);
            for (Product product : productsRepository.findAllByCategory(
                    categoriesRepository.findById(categoryID).get())) {
                product.setCategory(category);
                productsRepository.save(product);
            }
            return ResponseEntity.ok("\"status\": \"updated\"");
        }
        return ResponseEntity.ok("\"status\": \"category not existing\"");
    }

    @GetMapping(value = "/api/category/{categoryName}")
    @ResponseBody
    public ResponseEntity<Category> getCategoryByCategoryName(@PathVariable String categoryName){
        return ResponseEntity.ok(categoriesRepository.findByCategoryName(categoryName));
    }

    @DeleteMapping(value = "/api/category/{categoryID}")
    @ResponseBody
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryID){
        Category category = categoriesRepository.findById(categoryID).orElse(null);
        if(category != null){
            categoriesRepository.delete(category);
            for (Product product : productsRepository.findAllByCategory(category)) {
                product.setCategory(null);
                productsRepository.save(product);
            }
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"category not existing\"");
    }
}
