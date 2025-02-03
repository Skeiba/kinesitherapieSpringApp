package com.miniProjet.kinesitherapie.model.repositories;

import com.miniProjet.kinesitherapie.model.entities.FicheMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FicheMedicalRepository extends JpaRepository<FicheMedical, Long> {
    List<FicheMedical> findByPatient_Id(Long patientId);
}
