package com.miniProjet.kinesitherapie.model.repositories;

import com.miniProjet.kinesitherapie.model.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByPatient_Id(Long patientId);
}
