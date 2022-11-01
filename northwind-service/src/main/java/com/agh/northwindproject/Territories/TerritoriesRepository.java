package com.agh.northwindproject.Territories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TerritoriesRepository extends MongoRepository<Territory, String> {
    List<Territory> findAll();
    Territory findByTerritoryDescription(String categoryName);
}
