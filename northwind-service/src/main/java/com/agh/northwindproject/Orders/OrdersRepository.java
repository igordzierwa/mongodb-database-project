package com.agh.northwindproject.Orders;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdersRepository extends MongoRepository<Order, String> {
    List<Order> findAll();
    List<Order> findByCustomerIDAndEmployeeID(String customerID, String employeeID);
}
