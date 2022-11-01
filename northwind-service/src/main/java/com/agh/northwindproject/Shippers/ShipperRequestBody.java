package com.agh.northwindproject.Shippers;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class ShipperRequestBody {
    private String companyName;

    private String phone;
}
