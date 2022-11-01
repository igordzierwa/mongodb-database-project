package com.agh.northwindproject.Region;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegionsRepository extends MongoRepository<Region, String> {
    List<Region> findAll();
    Region findByRegionDescription(String regionDescription);
}
