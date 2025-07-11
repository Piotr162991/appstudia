package com.agency.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeResponseDTO> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeRequestDTO requestDTO) {
        EmployeeResponseDTO created = employeeService.create(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {
        return ResponseEntity.ok(employeeService.update(id, requestDTO));
    }

    @PostMapping("/{id}/apply/{jobOfferId}")
    public ResponseEntity<Void> applyForJob(
            @PathVariable UUID id,
            @PathVariable UUID jobOfferId) {
        employeeService.applyForJob(id, jobOfferId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/withdraw/{jobOfferId}")
    public ResponseEntity<Void> withdrawApplication(
            @PathVariable UUID id,
            @PathVariable UUID jobOfferId) {
        employeeService.withdrawApplication(id, jobOfferId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/applications")
    public ResponseEntity<Set<EmployeeResponseDTO.JobOfferDTO>> getAppliedJobs(@PathVariable UUID id) {
        return ResponseEntity.ok(employeeService.getAppliedJobs(id));
    }
}