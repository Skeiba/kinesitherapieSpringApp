package com.miniProjet.kinesitherapie.model.repository;

import com.miniProjet.kinesitherapie.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
