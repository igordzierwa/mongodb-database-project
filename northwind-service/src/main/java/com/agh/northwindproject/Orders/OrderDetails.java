package com.agh.northwindproject.Orders;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder
public class OrderDetails {
    private String productName;
    private double unitPrice;
    private int quantity;
    private double discount;

    public OrderDetails(String productName, OrderDetailsRequestBody orderDetailsRequestBody) {
        this.productName = productName;
        this.quantity = orderDetailsRequestBody.getQuantity();
        this.discount = orderDetailsRequestBody.getDiscount();
    }
}
