package com.agh.northwindproject.Employees;

import com.agh.northwindproject.Territories.TerritoriesRepository;
import com.agh.northwindproject.Territories.Territory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class EmployeesController {
    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private TerritoriesRepository territoriesRepository;

    @GetMapping(value = "/api/employees")
    @ResponseBody
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeesRepository.findAll());
    }

    @PostMapping(value = "/api/employee")
    @ResponseBody
    public ResponseEntity<String> addNewEmployee(@RequestBody EmployeeRequestBody employeeRequestBody){
        Employee employee = new Employee(employeeRequestBody);

        for (String territoryDescription : employeeRequestBody.getEmployeeTerritories()) {
            Territory territory = territoriesRepository.findByTerritoryDescription(territoryDescription);
            if (territory != null) {
                employee.getEmployeeTerritories().add(territory);
            }
        }
        employeesRepository.save(employee);

        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/employee/{lastName}/{firstName}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeByLastNameAndFirstName(@PathVariable String lastName,
                                                         @PathVariable String firstName){
        return ResponseEntity.ok(employeesRepository.findByLastNameAndFirstName(lastName, firstName));
    }

    @GetMapping(value = "/api/employee/id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Employee>> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(employeesRepository.findById(id));
    }


    @DeleteMapping(value = "/api/employee/{employeeID}")
    @ResponseBody
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeID){
        Employee employee = employeesRepository.findById(employeeID).orElse(null);
        if(employee != null){
            employeesRepository.delete(employee);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"employee not existing\"");
    }
}
