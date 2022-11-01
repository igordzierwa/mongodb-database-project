package com.agh.northwindproject.Products;

import com.agh.northwindproject.Categories.CategoriesRepository;
import com.agh.northwindproject.Categories.Category;
import com.agh.northwindproject.Suppliers.Supplier;
import com.agh.northwindproject.Suppliers.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private SuppliersRepository suppliersRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping(value = "/api/products")
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productsRepository.findAll());
    }

    @PostMapping(value = "/api/product")
    @ResponseBody
    public ResponseEntity<String> addNewProduct(@RequestBody ProductsRequestBody productsRequestBody){
        Product product = new Product(productsRequestBody);
        Category category = categoriesRepository.findByCategoryName(productsRequestBody.getCategoryName());
        Supplier supplier = suppliersRepository.findByCompanyName(productsRequestBody.getSupplierName());
        if(productsRepository.findByProductName(productsRequestBody.getProductName()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"product with this name already exists\"");
        }
        if (category != null && supplier != null) {
            product.setCategory(category);
            product.setSupplierID(supplier.getId());
            productsRepository.save(product);
            return ResponseEntity.ok("\"status\": \"added\"");
        } else if (category == null && supplier != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"category does not exists\"");
        } else if (category != null && supplier == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"supplier does not exists\"");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"supplier and category does not exists\"");
    }

    @PutMapping(value = "/api/product/{productID}")
    @ResponseBody
    public ResponseEntity<String> updateProduct(@PathVariable String productID,
                                                @RequestBody ProductsRequestBody productsRequestBody) {
        if(productsRepository.findById(productID).orElse(null) != null) {
            Product product = new Product(productsRequestBody);
            product.setId(productID);
            Category category = categoriesRepository.findByCategoryName(productsRequestBody.getCategoryName());
            Supplier supplier = suppliersRepository.findByCompanyName(productsRequestBody.getSupplierName());
            if (category != null && supplier != null) {
                product.setCategory(category);
                product.setSupplierID(supplier.getId());
                productsRepository.save(product);
                return ResponseEntity.ok("\"status\": \"updated\"");
            } else if (category == null && supplier != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"category does not exsist\"");
            } else if (category != null && supplier == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"supplier does not exsist\"");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"supplier and category does not exsist\"");
        }
        return ResponseEntity.ok("\"status\": \"product does not exists\"");
    }

    @GetMapping(value = "/api/product/id/{productID}")
    @ResponseBody
    public ResponseEntity<Product> getProductById(@PathVariable String productID){
        Product product = productsRepository.findById(productID).orElse(null);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/api/product/{productName}")
    @ResponseBody
    public ResponseEntity<Product> getProductByProductName(@PathVariable String productName){
        return ResponseEntity.ok(productsRepository.findByProductName(productName));
    }

    @DeleteMapping(value = "/api/product/{productID}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable String productID){
        Product product = productsRepository.findById(productID).orElse(null);
        if(product != null){
            productsRepository.delete(product);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"product already not existing, cannot remove\"");
    }

}
