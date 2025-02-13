package com.miniProjet.kinesitherapie.model.repositories;

import com.miniProjet.kinesitherapie.model.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByPatient_Id(Long patientId);
    List<RendezVous> findBySalle_Id(Long salleId);
    boolean existsBySalle_IdAndDateHeureBetween(Long salleId, LocalDateTime start, LocalDateTime end);
}
