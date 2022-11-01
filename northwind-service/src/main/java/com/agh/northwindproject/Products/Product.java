package com.agh.northwindproject.Products;

import com.agh.northwindproject.Categories.Category;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class Product {
    @Id
    private String id;

    private String productName;

    private String supplierID;

    @DBRef
    private Category category;

    private int quantityPerUnit;

    private double unitPrice;

    private int unitsInStock;

    private int unitsInOrder;

    private int reorderLevel;

    private boolean discontinued;

    public Product(ProductsRequestBody productsRequestBody) {
        this.productName = productsRequestBody.getProductName();
        this.quantityPerUnit = productsRequestBody.getQuantityPerUnit();
        this.unitPrice = productsRequestBody.getUnitPrice();
        this.unitsInStock = productsRequestBody.getUnitsInStock();
        this.unitsInOrder = productsRequestBody.getUnitsInOrder();
        this.reorderLevel = productsRequestBody.getReorderLevel();
        this.discontinued = productsRequestBody.isDiscontinued();
    }
}
