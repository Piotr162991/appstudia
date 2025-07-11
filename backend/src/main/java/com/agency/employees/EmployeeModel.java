
package com.agency.employees;

import com.agency.base.BaseEntity;
import com.agency.offer.JobOfferModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class EmployeeModel extends BaseEntity {
    
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    
    @NotNull
    private String email;
    
    @NotNull
    private String phone;
    
    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    private String address;
    
    private String city;

    @Column(name = "postal_code")
    private String postalCode;
    
    private String resume;
    
    @ManyToMany
    @JoinTable(
        name = "employee_job_applications",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "job_offer_id")
    )
    private Set<JobOfferModel> appliedJobs = new HashSet<>();
}