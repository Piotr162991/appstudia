package com.agency.employer;

import com.agency.offer.JobOfferModel;
import com.agency.offer.JobOfferResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployerMapper {
    
    public EmployerResponseDTO toResponseDTO(EmployerModel model) {
        if (model == null) return null;
        
        EmployerResponseDTO dto = new EmployerResponseDTO();
        dto.setId(model.getId());
        dto.setCompanyName(model.getCompanyName());
        dto.setAddress(model.getAddress());
        dto.setCity(model.getCity());
        dto.setPostalCode(model.getPostalCode());
        dto.setPhone(model.getPhone());
        dto.setEmail(model.getEmail());
        dto.setWebsite(model.getWebsite());
        dto.setCompanyDescription(model.getCompanyDescription());
        dto.setFoundedYear(model.getFoundedYear());
        dto.setIndustry(model.getIndustry());
        dto.setNumberOfEmployees(model.getNumberOfEmployees());

        
        if (model.getJobOffers() != null) {
            List<EmployerResponseDTO.JobOfferDTO> jobOfferDTOs = model.getJobOffers().stream()
                .map(this::toJobOfferDTO)
                .collect(Collectors.toList());
            dto.setJobOffer(jobOfferDTOs);
        }
        
        return dto;
    }

    public JobOfferResponseDTO.EmployerResponse toEmployerResponse(EmployerModel model) {
        if (model == null) return null;
        
        JobOfferResponseDTO.EmployerResponse response = new JobOfferResponseDTO.EmployerResponse();
        response.setId(model.getId());
        response.setCompanyName(model.getCompanyName());
        response.setIndustry(model.getIndustry());
        return response;
    }

    private EmployerResponseDTO.JobOfferDTO toJobOfferDTO(JobOfferModel jobOffer) {
        EmployerResponseDTO.JobOfferDTO dto = new EmployerResponseDTO.JobOfferDTO();
        dto.setId(jobOffer.getId());
        dto.setTitle(jobOffer.getTitle());
        dto.setDescription(jobOffer.getDescription());
        dto.setLocation(jobOffer.getLocation());
        dto.setEmploymentType(jobOffer.getEmploymentType());
        dto.setPublicationDate(jobOffer.getPublicationDate());
        dto.setExpirationDate(jobOffer.getExpirationDate());
        return dto;
    }

    public EmployerModel toModel(EmployerRequestDTO dto) {
        if (dto == null) return null;
        
        EmployerModel model = new EmployerModel();
        updateModel(model, dto);
        return model;
    }

    public void updateModel(EmployerModel model, EmployerRequestDTO dto) {
        model.setCompanyName(dto.getCompanyName());
        model.setAddress(dto.getAddress());
        model.setCity(dto.getCity());
        model.setPostalCode(dto.getPostalCode());
        model.setPhone(dto.getPhone());
        model.setEmail(dto.getEmail());
        model.setWebsite(dto.getWebsite());
        model.setCompanyDescription(dto.getCompanyDescription());
        model.setFoundedYear(dto.getFoundedYear());
        model.setIndustry(dto.getIndustry());
        model.setNumberOfEmployees(dto.getNumberOfEmployees());
    }
}