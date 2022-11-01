package com.agh.northwindproject.Shippers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShippersRepository extends MongoRepository<Shipper, String> {
    List<Shipper> findAll();
    Shipper findByCompanyName(String companyName);
}
