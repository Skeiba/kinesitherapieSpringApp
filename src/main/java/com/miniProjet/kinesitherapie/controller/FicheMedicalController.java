package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.dto.CreateFicheMedicalDTO;
import com.miniProjet.kinesitherapie.model.dto.FicheMedicalInfoDTO;
import com.miniProjet.kinesitherapie.services.interfaces.FicheMedicalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/fiche-medical")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
public class FicheMedicalController {
    private final FicheMedicalService ficheMedicalService;

    @PostMapping
    public ResponseEntity<FicheMedicalInfoDTO> createFicheMedical(@RequestBody CreateFicheMedicalDTO ficheMedicalDTO) {
        return ResponseEntity.ok(ficheMedicalService.createFicheMedical(ficheMedicalDTO));
    }

    @GetMapping
    public ResponseEntity<List<FicheMedicalInfoDTO>> getAllFicheMedical(@PathVariable Long patientId) {
        return ResponseEntity.ok(ficheMedicalService.getAllFicheMedical(patientId));
    }
}
