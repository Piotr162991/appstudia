package com.agency.employer;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;
    private final EmployerMapper employerMapper;

    public List<EmployerResponseDTO> getAll() {
        return employerRepository.findAll().stream()
                .map(employerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EmployerResponseDTO create(EmployerRequestDTO requestDTO) {
        EmployerModel employer = employerMapper.toModel(requestDTO);
        EmployerModel saved = employerRepository.save(employer);
        return employerMapper.toResponseDTO(saved);
    }

    public EmployerResponseDTO update(UUID id, EmployerRequestDTO requestDTO) {
        EmployerModel employer = employerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employer not found with id: " + id));

        employerMapper.updateModel(employer, requestDTO);
        EmployerModel updated = employerRepository.save(employer);
        return employerMapper.toResponseDTO(updated);
    }
}