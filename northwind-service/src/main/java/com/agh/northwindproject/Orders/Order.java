package com.agh.northwindproject.Orders;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "orders")
@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class Order {
    @Id
    private String id;

    private String customerID;

    private String employeeID;

    private String shipperID;

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

    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Order(OrderRequestBody orderRequestBody) {
        this.orderDate = orderRequestBody.getOrderDate();
        this.requiredDate = orderRequestBody.getRequiredDate();
        this.shippedDate = orderRequestBody.getShippedDate();
        this.freight = orderRequestBody.getFreight();
        this.shipName = orderRequestBody.getShipName();
        this.shipAddress = orderRequestBody.getShipAddress();
        this.shipCity = orderRequestBody.getShipCity();
        this.shipRegion = orderRequestBody.getShipRegion();
        this.shipPostalCode = orderRequestBody.getShipPostalCode();
        this.shipCountry = orderRequestBody.getShipCountry();
    }
}
