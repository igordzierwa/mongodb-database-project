package com.agh.northwindproject.Region;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class RegionRequestBody {
    private String regionDescription;

}
