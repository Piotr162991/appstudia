package com.agency.employer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employers")
@RequiredArgsConstructor
public class EmployerController {
    private final EmployerService employerService;

    @GetMapping
    public List<EmployerResponseDTO> getAll() {
        return employerService.getAll();
    }

    @PostMapping
    public ResponseEntity<EmployerResponseDTO> create( @RequestBody EmployerRequestDTO requestDTO) {
        EmployerResponseDTO created = employerService.create(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployerResponseDTO> update(
            @PathVariable UUID id,
             @RequestBody EmployerRequestDTO requestDTO) {
        EmployerResponseDTO updated = employerService.update(id, requestDTO);
        return ResponseEntity.ok(updated);
    }
}