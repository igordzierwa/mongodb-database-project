package com.agh.northwindproject.CustomerDemographics;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerDemographicsRepository extends MongoRepository<CustomerDemographic, String> {
    List<CustomerDemographic> findAll();
    CustomerDemographic findByCustomerDesc(String customerDesc);
}
