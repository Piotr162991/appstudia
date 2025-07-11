package com.agency.offer;

import com.agency.employer.EmployerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobOfferMapper {
    
    public JobOfferResponseDTO toResponseDTO(JobOfferModel model) {
        if (model == null) return null;
        
        JobOfferResponseDTO dto = new JobOfferResponseDTO();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());
        dto.setSalaryFrom(model.getSalaryFrom());
        dto.setSalaryTo(model.getSalaryTo());
        dto.setLocation(model.getLocation());
        dto.setEmploymentType(model.getEmploymentType());
        dto.setPublicationDate(model.getPublicationDate());
        dto.setExpirationDate(model.getExpirationDate());

        
        if (model.getEmployer() != null) {
            dto.setEmployer(toEmployerResponse(model.getEmployer()));
        }
        
        return dto;
    }
    
    private JobOfferResponseDTO.EmployerResponse toEmployerResponse(EmployerModel employer) {
        JobOfferResponseDTO.EmployerResponse response = new JobOfferResponseDTO.EmployerResponse();
        response.setId(employer.getId());
        response.setCompanyName(employer.getCompanyName());
        response.setIndustry(employer.getIndustry());
        return response;
    }

    public JobOfferModel toModel(JobOfferRequestDTO dto) {
        if (dto == null) return null;
        
        JobOfferModel model = new JobOfferModel();
        updateModel(model, dto);
        return model;
    }

    public void updateModel(JobOfferModel model, JobOfferRequestDTO dto) {
        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        model.setSalaryFrom(dto.getSalaryFrom());
        model.setSalaryTo(dto.getSalaryTo());
        model.setLocation(dto.getLocation());
        model.setEmploymentType(dto.getEmploymentType());
        model.setPublicationDate(dto.getPublicationDate());
        model.setExpirationDate(dto.getExpirationDate());
    }
}