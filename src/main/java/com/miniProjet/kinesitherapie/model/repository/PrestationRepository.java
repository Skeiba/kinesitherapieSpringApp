package com.miniProjet.kinesitherapie.model.repository;

import com.miniProjet.kinesitherapie.model.entity.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestationRepository extends JpaRepository<Prestation, Long> {
}
