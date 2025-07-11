package com.agency.employees;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String address;
    private String city;
    private String postalCode;
    private String resume;
}