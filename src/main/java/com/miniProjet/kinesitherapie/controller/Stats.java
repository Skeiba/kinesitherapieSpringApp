package com.miniProjet.kinesitherapie.controller;

import com.miniProjet.kinesitherapie.model.repositories.PrestationRepository;
import com.miniProjet.kinesitherapie.services.interfaces.PatientService;
import com.miniProjet.kinesitherapie.services.interfaces.SalleService;
import com.miniProjet.kinesitherapie.services.interfaces.UtilisateurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class Stats {
    private final UtilisateurService utilisateurService;
    private final PatientService patientService;
    private final SalleService salleService;
    private final PrestationRepository prestationRepository;


    @GetMapping
    public ResponseEntity<Map<String, Long>> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("patients", patientService.countPatients());
        stats.put("utilisateur", utilisateurService.countUtilisateurs());
        stats.put("prestation", prestationRepository.count());
        stats.put("salles", salleService.countAllSalles());
        return ResponseEntity.ok(stats);
    }
}
