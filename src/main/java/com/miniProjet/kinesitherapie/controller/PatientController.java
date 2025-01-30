package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.dto.PageResponse;
import com.miniProjet.kinesitherapie.model.dto.PatientDTO;
import com.miniProjet.kinesitherapie.services.interfaces.PatientService;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
public class PatientController {

    private final PatientService patientService;


    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO createdPatient = patientService.createPatient(patientDTO);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        return ResponseEntity.ok(patientDTO);
    }

    @GetMapping
    public ResponseEntity<PageResponse<PatientDTO>> getAllPatients(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int page,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int size) {
        Page<PatientDTO> patients = patientService.getAllPatients(page, size);
        PageResponse<PatientDTO> response = new PageResponse<>(
                patients.getContent(),
                patients.getNumber(),
                patients.getSize(),
                patients.getTotalElements(),
                patients.getTotalPages(),
                patients.isLast()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sorted")
    public ResponseEntity<PageResponse<PatientDTO>> getAllPatientsSorted(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int page,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int size,
            @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String direction
    ) {
        Page<PatientDTO> patients = patientService.getAllPatientsSorted(page, size, sortBy, direction);
        PageResponse<PatientDTO> response = new PageResponse<>(
                patients.getContent(),
                patients.getNumber(),
                patients.getSize(),
                patients.getTotalElements(),
                patients.getTotalPages(),
                patients.isLast()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientDTO> updatePatient(
            @PathVariable Long id,
            @RequestBody PatientDTO patientDTO) {
        PatientDTO updatedPatient = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        log.info("PathVariable ID: {}", id);
        log.info("User: {}", SecurityContextHolder.getContext().getAuthentication());
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
