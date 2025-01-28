package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.dto.PatientDTO;
import com.miniProjet.kinesitherapie.services.interfaces.PatientService;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //TODO : add the role based security mapping

    @PostMapping
    @Operation(summary = "create new patient")
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
    public ResponseEntity<Page<PatientDTO>> getAllPatients(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int page,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int size) {
        Page<PatientDTO> patients = patientService.getAllPatients(page, size);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/sorted")
    public ResponseEntity<Page<PatientDTO>> getAllPatientsSorted(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int page,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int size,
            @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String direction
    ) {
        Page<PatientDTO> patients = patientService.getAllPatientsSorted(page, size, sortBy, direction);
        return ResponseEntity.ok(patients);
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
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
