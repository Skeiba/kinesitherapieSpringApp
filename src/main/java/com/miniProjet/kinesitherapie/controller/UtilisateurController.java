package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.dto.PageResponse;
import com.miniProjet.kinesitherapie.model.dto.UserUpdateDTO;


import com.miniProjet.kinesitherapie.model.dto.UtilisateurDTO;
import com.miniProjet.kinesitherapie.services.interfaces.UtilisateurService;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.Valid;
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
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@PathVariable Long id) {
        return new ResponseEntity<>(utilisateurService.getUtilisateur(id), HttpStatus.OK);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO utilisateurDTO) {
        UtilisateurDTO updated = utilisateurService.updateUtilisateur(id, utilisateurDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
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
