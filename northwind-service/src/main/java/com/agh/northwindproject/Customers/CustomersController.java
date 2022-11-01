package com.agh.northwindproject.Customers;

import com.agh.northwindproject.CustomerDemographics.CustomerDemographic;
import com.agh.northwindproject.CustomerDemographics.CustomerDemographicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class CustomersController {
    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private CustomerDemographicsRepository customerDemographicsRepository;

    @GetMapping(value = "/api/customers")
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customersRepository.findAll());
    }

    @PostMapping(value = "/api/customer")
    @ResponseBody
    public ResponseEntity<String> addNewCustomer(@RequestBody CustomerRequestBody customerRequestBody){
        Customer customer = new Customer(customerRequestBody);
        for (String customerDesc : customerRequestBody.getCustomerCustomerDemo()) {
            CustomerDemographic customerDemographic = customerDemographicsRepository.findByCustomerDesc(customerDesc);
            if (customerDemographic != null ) {
                customer.getCustomerDemographics().add(customerDemographic);
            }
        }
        customersRepository.save(customer);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/customer/id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(customersRepository.findById(id));
    }

    @GetMapping(value = "/api/customer/{companyName}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomerByCompanyName(@PathVariable String companyName){
        return ResponseEntity.ok(customersRepository.findByCompanyName(companyName));
    }

    @DeleteMapping(value = "/api/customer/{customerID}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerID){
        Customer customer = customersRepository.findById(customerID).orElse(null);
        if(customer != null){
            customersRepository.delete(customer);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"customer not existing\"");
    }
}
