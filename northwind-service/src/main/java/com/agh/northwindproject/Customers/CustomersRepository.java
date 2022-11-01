package com.agh.northwindproject.Customers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomersRepository extends MongoRepository<Customer, String> {
    List<Customer> findAll();
    Customer findByCompanyName(String companyName);
}
