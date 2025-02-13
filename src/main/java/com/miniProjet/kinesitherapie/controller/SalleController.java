package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.dto.SalleDTO;
import com.miniProjet.kinesitherapie.model.entities.Salle;
import com.miniProjet.kinesitherapie.services.interfaces.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {

    @Autowired
    private SalleService salleService;


    @GetMapping
    public ResponseEntity<List<SalleDTO>> getAllSalles() {
        return ResponseEntity.ok(salleService.getAllSalles());
    }


    @GetMapping("/{id}")
    public SalleDTO getSalleById(@PathVariable Long id) {
        return salleService.getSalleById(id);
    }


    @PostMapping
    public SalleDTO createSalle(@RequestBody SalleDTO salle) {
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
}
