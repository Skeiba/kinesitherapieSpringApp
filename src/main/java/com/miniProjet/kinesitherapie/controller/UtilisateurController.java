package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.dto.PageResponse;
import com.miniProjet.kinesitherapie.model.dto.UserUpdateDTO;


import com.miniProjet.kinesitherapie.model.dto.UtilisateurDTO;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import com.miniProjet.kinesitherapie.services.interfaces.UtilisateurService;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

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
//    @GetMapping("/{id}")
//    public ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable Long id) {
//        return new ResponseEntity<>(utilisateurService.getUtilisateur(id), HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getSecretaireById(@PathVariable Long id) {
        Optional<Utilisateur> secretaire = utilisateurService.findById(id);
        return secretaire.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping
    public ResponseEntity<PageResponse<UtilisateurDTO>> getAllUtilisateurs(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) int page,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) int size
    ) {
        Page<UtilisateurDTO> utilisateurs = utilisateurService.getAllUtilisateurs(page, size);
        PageResponse<UtilisateurDTO> response = new PageResponse<>(
                utilisateurs.getContent(),
                utilisateurs.getNumber(),
                utilisateurs.getSize(),
                utilisateurs.getTotalElements(),
                utilisateurs.getTotalPages(),
                utilisateurs.isLast()
        );
        return ResponseEntity.ok(response);
    }
    // 🔹 Update a Secretaire
    @PutMapping("/secretaires/{id}")
    public ResponseEntity<Utilisateur> updateSecretaire(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> updatedSecretaire = utilisateurService.updateSecretaire(id, utilisateur);
        return updatedSecretaire.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO utilisateurDTO) {
        UtilisateurDTO updated = utilisateurService.updateUtilisateur(id, utilisateurDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    // 🔹 Delete a Secretaire
    @DeleteMapping("/secretaires/{id}")
    public ResponseEntity<Void> deleteSecretaire(@PathVariable Long id) {
        boolean isDeleted = utilisateurService.deleteSecretaire(id);
        return isDeleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteUtilisateur(@PathVariable Long id) {
        boolean isRemoved = utilisateurService.removeUtilisateur(id);
        if (isRemoved) {
            return ResponseEntity.ok(Map.of("message", "Utilisateur removed"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Utilisateur not found"));
        }
    }

}
