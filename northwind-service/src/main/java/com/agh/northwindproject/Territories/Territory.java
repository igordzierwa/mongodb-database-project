package com.agh.northwindproject.Territories;

import com.agh.northwindproject.Region.Region;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "territories")
@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class Territory {
    @Id
    private String id;

    private String territoryDescription;

    @DBRef
    private Region region;

    public Territory(TerritoriesRequestBody territoriesRequestBody) {
        this.territoryDescription = territoriesRequestBody.getTerritoryDescription();
    }
}
