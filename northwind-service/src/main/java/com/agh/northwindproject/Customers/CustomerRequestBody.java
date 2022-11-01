package com.agh.northwindproject.Customers;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class CustomerRequestBody {
    private String companyName;

    private String contactName;

    private String contactTitle;

    private String address;

    private String city;

    private String Region;

    private String postalCode;

    private String country;

    private String phone;

    private String fax;

    private List<String> customerCustomerDemo = new ArrayList<>();
}
