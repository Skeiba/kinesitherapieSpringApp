package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.dto.*;
import com.miniProjet.kinesitherapie.services.interfaces.RendezVousService;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/rendez-vous")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
public class RendezVousController {
    private final RendezVousService rendezVousService;

    @GetMapping
    public ResponseEntity<PageResponse<RendezVousDTO>> getAllSalles(@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int page,
                                                               @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int size) {
        Page<RendezVousDTO> rendezVous = rendezVousService.getAllRendezVous(page, size);
        PageResponse<RendezVousDTO> response = new PageResponse<>(
                rendezVous.getContent(),
                rendezVous.getNumber(),
                rendezVous.getSize(),
                rendezVous.getTotalElements(),
                rendezVous.getTotalPages(),
                rendezVous.isLast()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RendezVousDTO> createRendezVous(@RequestBody CreateRendezVousDTO createRendezVous) {
        RendezVousDTO created = rendezVousService.createRendezVous(createRendezVous);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/new-patient")
    public ResponseEntity<RendezVousDTO> createRendezVousWithNewPatient(@RequestBody PatientRendezVousDTO dto) {
        RendezVousDTO created = rendezVousService.createRendezVousWithNewPatient(dto);
        return ResponseEntity.ok(created);
    }



    @PutMapping("/{id}")
    public ResponseEntity<RendezVousDTO> updateRendezVous(@PathVariable Long id, @RequestBody RendezVousUpdateDTO dto) {
        RendezVousDTO updated = rendezVousService.updateRendezVous(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteRendezVous(@PathVariable Long id) {
        boolean isDeleted = rendezVousService.deleteRendezVous(id);
        if (isDeleted) {
            return ResponseEntity.ok(Map.of("message","Rendez-vous deleted successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Rendez-vous not found."));
        }
    }
}
