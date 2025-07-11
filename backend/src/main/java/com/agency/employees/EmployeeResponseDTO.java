package com.agency.employees;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class EmployeeResponseDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String address;
    private String city;
    private String postalCode;
    private String resume;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<JobOfferDTO> appliedJobs;
    
    @Data
    public static class JobOfferDTO {
        private UUID id;
        private String title;
        private String description;
        private String location;
        private String employmentType;
        private LocalDateTime publicationDate;
        private LocalDateTime expirationDate;
        private EmployerDTO employer;
        
        @Data
        public static class EmployerDTO {
            private UUID id;
            private String companyName;
            private String industry;
        }
    }
}