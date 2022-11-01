package com.agh.northwindproject.Categories;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class CategoryRequestBody {
    private String categoryName;

    private String description;

    private String picture;
}
