package com.miniProjet.kinesitherapie.model.repository;

import com.miniProjet.kinesitherapie.model.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByLogin(String login);
    List<Utilisateur> findAll();
}
