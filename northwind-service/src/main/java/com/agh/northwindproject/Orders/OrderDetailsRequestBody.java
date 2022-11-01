package com.agh.northwindproject.Orders;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@JsonPropertyOrder
public class OrderDetailsRequestBody {
    private String productName;

    private int quantity;

    private double discount;
}
