package com.agh.northwindproject.Shippers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ShippersController {
    @Autowired
    private ShippersRepository shippersRepository;

    @GetMapping(value = "/api/shippers")
    @ResponseBody
    public ResponseEntity<List<Shipper>> getAllShippers(){
        return ResponseEntity.ok(shippersRepository.findAll());
    }

    @PostMapping(value = "/api/shipper/")
    @ResponseBody
    public ResponseEntity<String> addNewShipper(@RequestBody ShipperRequestBody shipperRequestBody){
        shippersRepository.save(new Shipper(shipperRequestBody));
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/shipper/{companyName}")
    @ResponseBody
    public ResponseEntity<Shipper> getShipperByCompanyName(@PathVariable String companyName){
        return ResponseEntity.ok(shippersRepository.findByCompanyName(companyName));
    }

    @GetMapping(value = "/api/shipper/id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Shipper>> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(shippersRepository.findById(id));
    }

    @DeleteMapping(value = "/api/shipper/{shipperID}")
    @ResponseBody
    public ResponseEntity<String> deleteShipper(@PathVariable String shipperID){
        Shipper shipper = shippersRepository.findById(shipperID).orElse(null);
        if(shipper != null){
            shippersRepository.delete(shipper);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"shipper not existing\"");
    }
}
