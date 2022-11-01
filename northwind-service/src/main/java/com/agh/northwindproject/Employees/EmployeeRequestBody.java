package com.agh.northwindproject.Employees;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@JsonPropertyOrder
public class EmployeeRequestBody {
    private String lastName;

    private String firstName;

    private String title;

    private String titleOfCourtesy;

    private Date birthDate;

    private Date hireDate;

    private String address;

    private String city;

    private String regionDescription;

    private String postalCode;

    private String country;

    private String homePhone;

    private String extension;

    private String photo;

    private String notes;

    private String reportsTo;

    private String photoPath;

    private List<String> employeeTerritories = new ArrayList<>();
}
