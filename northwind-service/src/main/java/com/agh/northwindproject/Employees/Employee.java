package com.agh.northwindproject.Employees;

import com.agh.northwindproject.Territories.Territory;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "employees")
@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class Employee {
    @Id
    private String id;

    private String lastName;

    private String firstName;

    private String title;

    private String titleOfCourtesy;

    private Date birthDate;

    private Date hireDate;

    private String address;

    private String city;

    private String region;

    private String postalCode;

    private String country;

    private String homePhone;

    private String extension;

    private String photo;

    private String notes;

    private String reportsTo;

    private String photoPath;

    @DBRef
    private List<Territory> employeeTerritories = new ArrayList<>();

    public Employee(EmployeeRequestBody employeeRequestBody) {
        this.lastName = employeeRequestBody.getLastName();
        this.firstName = employeeRequestBody.getFirstName();
        this.title = employeeRequestBody.getTitle();
        this.titleOfCourtesy = employeeRequestBody.getTitleOfCourtesy();
        this.birthDate = employeeRequestBody.getBirthDate();
        this.hireDate = employeeRequestBody.getHireDate();
        this.address = employeeRequestBody.getAddress();
        this.city = employeeRequestBody.getCity();
        this.region = employeeRequestBody.getRegionDescription();
        this.postalCode = employeeRequestBody.getPostalCode();
        this.country = employeeRequestBody.getCountry();
        this.homePhone = employeeRequestBody.getHomePhone();
        this.extension = employeeRequestBody.getExtension();
        this.photo = employeeRequestBody.getPhoto();
        this.notes = employeeRequestBody.getNotes();
        this.reportsTo = employeeRequestBody.getReportsTo();
        this.photoPath = employeeRequestBody.getPhotoPath();
    }
}
