package com.miniProjet.kinesitherapie.controller;

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
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }


    @GetMapping("/{id}")
    public Salle getSalleById(@PathVariable Long id) {
        return salleService.getSalleById(id);
    }


    @PostMapping
    public Salle createSalle(@RequestBody Salle salle) {
        return salleService.createSalle(salle);
    }


    @PutMapping("/{id}")
    public Salle updateSalle(@PathVariable Long id, @RequestBody Salle salleDetails) {
        return salleService.updateSalle(id, salleDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSalle(@PathVariable Long id) {
        salleService.deleteSalle(id);
        return ResponseEntity.ok().build();
    }
}
