package com.agh.northwindproject.CustomerDemographics;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class CustomerDemographicsRequestBody {
    private String customerDesc;
}
