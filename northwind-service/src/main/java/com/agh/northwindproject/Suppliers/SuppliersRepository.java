package com.agh.northwindproject.Suppliers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SuppliersRepository extends MongoRepository<Supplier, String> {
    List<Supplier> findAll();
    Supplier findByCompanyName(String companyName);
}

