package com.agh.northwindproject.Suppliers;

import com.agh.northwindproject.Products.Product;
import com.agh.northwindproject.Products.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SuppliersController {

    @Autowired
    private SuppliersRepository suppliersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping(value = "/api/suppliers")
    @ResponseBody
    public ResponseEntity<List<Supplier>> getAllSuppliers(){
        return ResponseEntity.ok(suppliersRepository.findAll());
    }

    @PostMapping(value = "/api/supplier")
    @ResponseBody
    public ResponseEntity<String> addNewSupplier(@RequestBody SupplierRequestBody supplierRequestBody){
        suppliersRepository.save(new Supplier(supplierRequestBody));
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/supplier/{companyName}")
    @ResponseBody
    public ResponseEntity<Supplier> getSupplierByCompanyName(@PathVariable String companyName){
        return ResponseEntity.ok(suppliersRepository.findByCompanyName(companyName));
    }

    @PutMapping(value = "/api/supplier/{supplierID}")
    @ResponseBody
    public ResponseEntity<String> updateSupplier(@PathVariable String supplierID,
                                                 @RequestBody SupplierRequestBody supplierRequestBody) {
        if(suppliersRepository.findById(supplierID).orElse(null) != null) {
            Supplier supplier = new Supplier(supplierRequestBody);
            supplier.setId(supplierID);
            suppliersRepository.save(supplier);
            return ResponseEntity.ok("\"status\": \"updated\"");
        }
        return ResponseEntity.ok("\"status\": \"supplier not existing\"");
    }

    @DeleteMapping(value = "/api/supplier/{supplierID}")
    @ResponseBody
    public ResponseEntity<String> deleteSupplier(@PathVariable String supplierID){
        Supplier supplier = suppliersRepository.findById(supplierID).orElse(null);
        if(supplier != null){
            suppliersRepository.delete(supplier);
            for (Product product : productsRepository.findAllBySupplierID(supplierID)) {
                product.setSupplierID(null);
                productsRepository.save(product);
            }
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"supplier not existing\"");
    }
}
