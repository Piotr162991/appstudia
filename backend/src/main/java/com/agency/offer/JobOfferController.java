package com.agency.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/job-offers")
@RequiredArgsConstructor
public class JobOfferController {
    private final JobOfferService jobOfferService;

    @GetMapping
    public List<JobOfferResponseDTO> getAll() {
        return jobOfferService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOfferResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(jobOfferService.getById(id));
    }

    @PostMapping
    public ResponseEntity<JobOfferResponseDTO> create(@Valid @RequestBody JobOfferRequestDTO requestDTO) {
        JobOfferResponseDTO created = jobOfferService.create(requestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobOfferResponseDTO> update(
            @PathVariable UUID id,
            @Valid @RequestBody JobOfferRequestDTO requestDTO) {
        return ResponseEntity.ok(jobOfferService.update(id, requestDTO));
    }
}