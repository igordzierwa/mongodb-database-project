package com.agh.northwindproject.Suppliers;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "suppliers")
@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class Supplier {

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

    private String homePage;

    public Supplier(SupplierRequestBody supplierRequestBody) {
        this.companyName = supplierRequestBody.getCompanyName();
        this.contactName = supplierRequestBody.getContactName();
        this.contactTitle = supplierRequestBody.getContactTitle();
        this.address = supplierRequestBody.getAddress();
        this.city = supplierRequestBody.getCity();
        this.region = supplierRequestBody.getRegion();
        this.postalCode = supplierRequestBody.getPostalCode();
        this.country = supplierRequestBody.getCountry();
        this.phone = supplierRequestBody.getPhone();
        this.fax = supplierRequestBody.getFax();
        this.homePage = supplierRequestBody.getHomePage();
    }
}
