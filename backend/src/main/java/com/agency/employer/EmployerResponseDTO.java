package com.agency.employer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EmployerResponseDTO {
    private UUID id;
    private String companyName;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private String email;
    private String website;
    private String companyDescription;
    private Integer foundedYear;
    private String industry;
    private Integer numberOfEmployees;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<JobOfferDTO> jobOffer;

    @Data
    public static class JobOfferDTO {
        private UUID id;
        private String title;
        private String description;
        private String location;
        private String employmentType;
        private LocalDateTime publicationDate;
        private LocalDateTime expirationDate;
    }
}