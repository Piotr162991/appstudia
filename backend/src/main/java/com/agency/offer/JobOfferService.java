package com.agency.offer;

import com.agency.employer.EmployerModel;
import com.agency.employer.EmployerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobOfferService {
    private final JobOfferRepository jobOfferRepository;
    private final EmployerRepository employerRepository;
    private final JobOfferMapper jobOfferMapper;  // You'll need to create this

    public List<JobOfferResponseDTO> getAll() {
        return jobOfferRepository.findAll().stream()
                .map(jobOfferMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public JobOfferResponseDTO getById(UUID id) {
        JobOfferModel jobOffer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job offer not found with id: " + id));
        return jobOfferMapper.toResponseDTO(jobOffer);
    }

    public JobOfferResponseDTO create(JobOfferRequestDTO requestDTO) {
        EmployerModel employer = employerRepository.findById(requestDTO.getEmployerId())
                .orElseThrow(() -> new EntityNotFoundException("Employer not found with id: " + requestDTO.getEmployerId()));
        
        JobOfferModel jobOffer = jobOfferMapper.toModel(requestDTO);
        jobOffer.setEmployer(employer);
        
        JobOfferModel saved = jobOfferRepository.save(jobOffer);
        return jobOfferMapper.toResponseDTO(saved);
    }

    public JobOfferResponseDTO update(UUID id, JobOfferRequestDTO requestDTO) {
        JobOfferModel existing = jobOfferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job offer not found with id: " + id));
        
        EmployerModel employer = employerRepository.findById(requestDTO.getEmployerId())
                .orElseThrow(() -> new EntityNotFoundException("Employer not found with id: " + requestDTO.getEmployerId()));

        jobOfferMapper.updateModel(existing, requestDTO);
        existing.setEmployer(employer);
        
        JobOfferModel updated = jobOfferRepository.save(existing);
        return jobOfferMapper.toResponseDTO(updated);
    }
}