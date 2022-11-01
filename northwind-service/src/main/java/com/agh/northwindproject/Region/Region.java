package com.agh.northwindproject.Region;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "regions")
@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class Region {
    @Id
    private String id;

    private String regionDescription;

    public Region(RegionRequestBody regionDescription) {
        this.regionDescription = regionDescription.getRegionDescription();
    }
}
