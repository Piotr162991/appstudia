package com.agency.employees;

import com.agency.employer.EmployerModel;
import com.agency.offer.JobOfferModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    
    public EmployeeResponseDTO toResponseDTO(EmployeeModel model) {
        if (model == null) return null;
        
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setPhone(model.getPhone());
        dto.setDateOfBirth(model.getDateOfBirth());
        dto.setAddress(model.getAddress());
        dto.setCity(model.getCity());
        dto.setPostalCode(model.getPostalCode());
        dto.setResume(model.getResume());
        
        if (model.getAppliedJobs() != null) {
            dto.setAppliedJobs(model.getAppliedJobs().stream()
                    .map(this::toJobOfferDTO)
                    .collect(Collectors.toSet()));
        }
        
        return dto;
    }
    
    EmployeeResponseDTO.JobOfferDTO toJobOfferDTO(JobOfferModel jobOffer) {
        EmployeeResponseDTO.JobOfferDTO dto = new EmployeeResponseDTO.JobOfferDTO();
        dto.setId(jobOffer.getId());
        dto.setTitle(jobOffer.getTitle());
        dto.setDescription(jobOffer.getDescription());
        dto.setLocation(jobOffer.getLocation());
        dto.setEmploymentType(jobOffer.getEmploymentType());
        dto.setPublicationDate(jobOffer.getPublicationDate());
        dto.setExpirationDate(jobOffer.getExpirationDate());
        
        if (jobOffer.getEmployer() != null) {
            dto.setEmployer(toEmployerDTO(jobOffer.getEmployer()));
        }
        
        return dto;
    }
    
    private EmployeeResponseDTO.JobOfferDTO.EmployerDTO toEmployerDTO(EmployerModel employer) {
        EmployeeResponseDTO.JobOfferDTO.EmployerDTO dto = new EmployeeResponseDTO.JobOfferDTO.EmployerDTO();
        dto.setId(employer.getId());
        dto.setCompanyName(employer.getCompanyName());
        dto.setIndustry(employer.getIndustry());
        return dto;
    }

    public EmployeeModel toModel(EmployeeRequestDTO dto) {
        if (dto == null) return null;
        
        EmployeeModel model = new EmployeeModel();
        updateModel(model, dto);
        return model;
    }

    public void updateModel(EmployeeModel model, EmployeeRequestDTO dto) {
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setEmail(dto.getEmail());
        model.setPhone(dto.getPhone());
        model.setDateOfBirth(dto.getDateOfBirth());
        model.setAddress(dto.getAddress());
        model.setCity(dto.getCity());
        model.setPostalCode(dto.getPostalCode());
        model.setResume(dto.getResume());
    }
}