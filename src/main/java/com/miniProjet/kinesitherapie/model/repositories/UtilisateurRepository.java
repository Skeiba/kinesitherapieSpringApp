package com.miniProjet.kinesitherapie.model.repositories;

import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import com.miniProjet.kinesitherapie.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Modifying
    @Query("UPDATE Utilisateur u SET u.loggedIn = :isLoggedIn WHERE u.id = :id")
    void updateLoggedInStatus(Long id, boolean isLoggedIn);

    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findById(Long id);
    boolean existsByEmail(String email);
    List<Utilisateur> findAllByRole(Role role);
}
