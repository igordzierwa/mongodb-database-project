package com.agh.northwindproject.Orders;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class OrderRequestBody {
    private String customerCompanyName;

    private String employeeLastName;

    private String employeeFirstName;

    private String shipperCompanyName;

    private Date orderDate;

    private Date requiredDate;

    private Date shippedDate;

    private String freight;

    private String shipName;

    private String shipAddress;

    private String shipCity;

    private String shipRegion;

    private String shipPostalCode;

    private String shipCountry;

    private List<OrderDetailsRequestBody> orderDetails = new ArrayList<>();
}
