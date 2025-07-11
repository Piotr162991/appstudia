package com.agency.offer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class JobOfferRequestDTO {
    private String title;

    private String description;

    @NotNull
    private BigDecimal salaryFrom;

    @NotNull
    private BigDecimal salaryTo;


    private String location;

    private String employmentType;

    @NotNull
    private LocalDateTime publicationDate;

    @NotNull
    private LocalDateTime expirationDate;

    @NotNull
    private UUID employerId;
}

