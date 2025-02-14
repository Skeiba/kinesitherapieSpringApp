package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.dto.CreateSalleDTO;
import com.miniProjet.kinesitherapie.model.dto.PageResponse;
import com.miniProjet.kinesitherapie.model.dto.PatientDTO;
import com.miniProjet.kinesitherapie.model.dto.SalleDTO;
import com.miniProjet.kinesitherapie.model.entities.Salle;
import com.miniProjet.kinesitherapie.services.interfaces.SalleService;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
public class SalleController {

    private final SalleService salleService;


    @GetMapping
    public ResponseEntity<PageResponse<SalleDTO>> getAllSalles(@RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int page,
                                                               @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int size) {
        Page<SalleDTO> salles = salleService.getAllSalles(page, size);
        PageResponse<SalleDTO> response = new PageResponse<>(
                salles.getContent(),
                salles.getNumber(),
                salles.getSize(),
                salles.getTotalElements(),
                salles.getTotalPages(),
                salles.isLast()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/available")
    public ResponseEntity<List<SalleDTO>> getAvailableSalles() {
        return ResponseEntity.ok(salleService.getAvailableSalles());
    }

    @GetMapping("/{id}")
    public SalleDTO getSalleById(@PathVariable Long id) {
        return salleService.getSalleById(id);
    }


    @PostMapping
    public SalleDTO createSalle(@RequestBody CreateSalleDTO salle) {
        return salleService.createSalle(salle);
    }


    @PutMapping("/{id}")
    public SalleDTO updateSalle(@PathVariable Long id, @RequestBody SalleDTO salleDetails) {
        return salleService.updateSalle(id, salleDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSalle(@PathVariable Long id) {
        salleService.deleteSalle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countAllSalles() {
        return ResponseEntity.ok(salleService.countAllSalles());
    }
}
