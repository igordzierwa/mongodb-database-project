package com.agh.northwindproject.Customers;

import com.agh.northwindproject.CustomerDemographics.CustomerDemographic;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "customers")
@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class Customer {
    @Id
    private String id;

    private String companyName;

    private String contactName;

    private String contactTitle;

    private String address;

    private String city;

    private String region;

    private String postalCode;

    private String country;

    private String phone;

    private String fax;

    @DBRef
    private List<CustomerDemographic> customerDemographics = new ArrayList<>();

    public Customer(CustomerRequestBody customerRequestBody) {
        this.companyName = customerRequestBody.getCompanyName();
        this.contactName = customerRequestBody.getContactName();
        this.contactTitle = customerRequestBody.getContactTitle();
        this.address = customerRequestBody.getAddress();
        this.city = customerRequestBody.getCity();
        this.region = customerRequestBody.getRegion();
        this.postalCode = customerRequestBody.getPostalCode();
        this.country = customerRequestBody.getCountry();
        this.phone = customerRequestBody.getPhone();
        this.fax = customerRequestBody.getFax();
    }
}
