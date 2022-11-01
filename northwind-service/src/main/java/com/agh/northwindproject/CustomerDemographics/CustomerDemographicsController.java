package com.agh.northwindproject.CustomerDemographics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class CustomerDemographicsController {
   @Autowired
   private CustomerDemographicsRepository customerDemographicsRepository;

    @GetMapping(value = "/api/customerDemographics")
    @ResponseBody
    public ResponseEntity<List<CustomerDemographic>> getAllCustomerDemographics(){
        return ResponseEntity.ok(customerDemographicsRepository.findAll());
    }

    @PostMapping(value = "/api/customerDemographic")
    @ResponseBody
    public ResponseEntity<String> addNewCustomerDemographic(@RequestBody CustomerDemographicsRequestBody customerDemographicsRequestBody){
        customerDemographicsRepository.save(new CustomerDemographic(customerDemographicsRequestBody));
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/customerDemographic/{customerDesc}")
    @ResponseBody
    public ResponseEntity<CustomerDemographic> getCustomerDemographicByDescription(@PathVariable String customerDesc){
        return ResponseEntity.ok(customerDemographicsRepository.findByCustomerDesc(customerDesc));
    }

    @DeleteMapping(value = "/api/customerDemographic/{customerDemographicID}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomerDemographic(@PathVariable String customerDemographicID){
        CustomerDemographic customerDemographic = customerDemographicsRepository.
                findById(customerDemographicID).orElse(null);
        if(customerDemographic != null){
            customerDemographicsRepository.delete(customerDemographic);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"customerDemographic not existing\"");
    }
}
