package com.agh.northwindproject.Employees;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeesRepository extends MongoRepository<Employee, String> {
    List<Employee> findAll();
    Employee findByLastNameAndFirstName(String lastName, String firstName);
}
