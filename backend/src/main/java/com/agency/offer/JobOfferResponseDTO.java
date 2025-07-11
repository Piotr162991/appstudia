package com.agency.offer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class JobOfferResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private BigDecimal salaryFrom;
    private BigDecimal salaryTo;
    private String location;
    private String employmentType;
    private LocalDateTime publicationDate;
    private LocalDateTime expirationDate;
    private EmployerResponse employer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    public static class EmployerResponse{
        private UUID id;
        private String companyName;
        private String industry;
    }
}
