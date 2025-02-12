package com.miniProjet.kinesitherapie.controller;



import com.miniProjet.kinesitherapie.model.dto.UtilisateurDTO;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import com.miniProjet.kinesitherapie.services.interfaces.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/secretaires")
    public ResponseEntity<List<Utilisateur>> getAllSecretaires() {
        List<Utilisateur> secretaires = utilisateurService.getAllSecretaires();
        return ResponseEntity.ok(secretaires);
    }


    // 🔹 Save a Secretaire
    @PostMapping("/saveSecretaire")
    public ResponseEntity<Utilisateur> saveSecretaire(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.saveSecretaire(utilisateur));

    }

    // 🔹 Get a Secretaire by ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getSecretaireById(@PathVariable Long id) {
        Optional<Utilisateur> secretaire = utilisateurService.findById(id);
        return secretaire.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 🔹 Update a Secretaire
    @PutMapping("/secretaires/{id}")
    public ResponseEntity<Utilisateur> updateSecretaire(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> updatedSecretaire = utilisateurService.updateSecretaire(id, utilisateur);
        return updatedSecretaire.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 🔹 Delete a Secretaire
    @DeleteMapping("/secretaires/{id}")
    public ResponseEntity<Void> deleteSecretaire(@PathVariable Long id) {
        boolean isDeleted = utilisateurService.deleteSecretaire(id);
        return isDeleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
