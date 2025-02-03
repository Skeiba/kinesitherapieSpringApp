package com.miniProjet.kinesitherapie.model.repositories;

import com.miniProjet.kinesitherapie.model.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByPatient_Id(Long patientId);

}
