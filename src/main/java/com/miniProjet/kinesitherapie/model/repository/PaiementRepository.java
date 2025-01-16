package com.miniProjet.kinesitherapie.model.repository;

import com.miniProjet.kinesitherapie.model.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
