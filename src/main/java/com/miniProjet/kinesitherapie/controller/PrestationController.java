package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.dto.CreatePrestationDTO;
import com.miniProjet.kinesitherapie.model.dto.PrestationDTO;
import com.miniProjet.kinesitherapie.services.interfaces.PrestationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prestations")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
public class PrestationController {
    private final PrestationService prestationService;

    @PostMapping
    public ResponseEntity<PrestationDTO> createPrestation(@RequestBody CreatePrestationDTO dto) {
        return ResponseEntity.ok(prestationService.createPrestation(dto));
    }

    @GetMapping
    public ResponseEntity<List<PrestationDTO>> getAllPrestations() {
        return ResponseEntity.ok(prestationService.getAllPrestations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestationDTO> getPrestationById(@PathVariable Long id) {
        return ResponseEntity.ok(prestationService.getPrestationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestationDTO> updatePrestation(@PathVariable Long id, @RequestBody PrestationDTO dto) {
        return ResponseEntity.ok(prestationService.updatePrestation(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePrestation(@PathVariable Long id) {
        boolean isDeleted = prestationService.deletePrestation(id);
        if (isDeleted) {
            return ResponseEntity.ok(Map.of("message","Prestation deleted successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Prestation not found."));
        }
    }
}
