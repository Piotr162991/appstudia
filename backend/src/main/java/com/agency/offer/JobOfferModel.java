
package com.agency.offer;

import com.agency.base.BaseEntity;
import com.agency.employer.EmployerModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "job_offers")
@Getter
@Setter
public class JobOfferModel extends BaseEntity {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Column(name = "salary_from")
    private BigDecimal salaryFrom;

    @Column(name = "salary_to")
    private BigDecimal salaryTo;

    @NotNull
    private String location;

    @NotNull
    @Column(name = "employment_type")
    private String employmentType;

    @NotNull
    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private EmployerModel employer;

}
