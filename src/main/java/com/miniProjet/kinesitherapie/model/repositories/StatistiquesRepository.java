package com.miniProjet.kinesitherapie.model.repositories;

import com.miniProjet.kinesitherapie.model.entities.Statistiques;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatistiquesRepository extends JpaRepository<Statistiques, Integer> {
}
