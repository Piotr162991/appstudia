package com.agency.employees;

import com.agency.offer.JobOfferModel;
import com.agency.offer.JobOfferRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JobOfferRepository jobOfferRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeResponseDTO> getAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDTO getById(UUID id) {
        EmployeeModel employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        return employeeMapper.toResponseDTO(employee);
    }

    @Transactional
    public EmployeeResponseDTO create(EmployeeRequestDTO requestDTO) {
        if (employeeRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Employee with this email already exists");
        }

        EmployeeModel employee = employeeMapper.toModel(requestDTO);
        EmployeeModel saved = employeeRepository.save(employee);
        return employeeMapper.toResponseDTO(saved);
    }

    @Transactional
    public EmployeeResponseDTO update(UUID id, EmployeeRequestDTO requestDTO) {
        EmployeeModel employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        employeeMapper.updateModel(employee, requestDTO);
        EmployeeModel updated = employeeRepository.save(employee);
        return employeeMapper.toResponseDTO(updated);
    }

    @Transactional
    public void applyForJob(UUID employeeId, UUID jobOfferId) {
        EmployeeModel employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));
        JobOfferModel jobOffer = jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new EntityNotFoundException("Job offer not found with id: " + jobOfferId));

        employee.getAppliedJobs().add(jobOffer);
        employeeRepository.save(employee);
    }

    @Transactional
    public void withdrawApplication(UUID employeeId, UUID jobOfferId) {
        EmployeeModel employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));
        JobOfferModel jobOffer = jobOfferRepository.findById(jobOfferId)
                .orElseThrow(() -> new EntityNotFoundException("Job offer not found with id: " + jobOfferId));

        employee.getAppliedJobs().remove(jobOffer);
        employeeRepository.save(employee);
    }

    public Set<EmployeeResponseDTO.JobOfferDTO> getAppliedJobs(UUID employeeId) {
        EmployeeModel employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + employeeId));
        return employee.getAppliedJobs().stream()
                .map(employeeMapper::toJobOfferDTO)
                .collect(Collectors.toSet());
    }
}