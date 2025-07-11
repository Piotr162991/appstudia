package com.agency.employer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployerRequestDTO {
    private String companyName;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private String email;
    private String website;
    private String companyDescription;
    @NotNull
    private Integer foundedYear;
    private String industry;
    private Integer numberOfEmployees;
}