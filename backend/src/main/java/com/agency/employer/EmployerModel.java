package com.agency.employer;

import com.agency.base.BaseEntity;
import com.agency.offer.JobOfferModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "employer")
@NoArgsConstructor
@AllArgsConstructor
public class EmployerModel extends BaseEntity {

    @NotNull
    @Column(name = "company_name")
    private String companyName;
    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    @Column(name = "postal_code")
    private String postalCode;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    private String website;
    @Column(name = "company_description")
    private String companyDescription;
    @NotNull
    @Column(name = "founded_year")
    private Integer foundedYear;
    @NotNull
    private String industry;
    @Column(name = "number_of_employees")
    private Integer numberOfEmployees;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobOfferModel> jobOffers = new ArrayList<>();

}
