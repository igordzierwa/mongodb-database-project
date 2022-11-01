package com.agh.northwindproject.CustomerDemographics;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customerDemographics")
@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class CustomerDemographic {
    @Id
    private String id;

    private String customerDesc;

    public CustomerDemographic(CustomerDemographicsRequestBody customerDemographicsRequestBody) {
        this.customerDesc = customerDemographicsRequestBody.getCustomerDesc();
    }
}
