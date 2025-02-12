package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.auth.dto.RegisterRequest;
import com.miniProjet.kinesitherapie.exceptions.EmailAlreadyExistsException;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;


public interface UtilisateurService extends UserDetailsService {
    void saveUtilisateur(Utilisateur utilisateur);
    Utilisateur registerUtilisateur(RegisterRequest registerRequest) throws EmailAlreadyExistsException;
    Utilisateur findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Utilisateur> findById(Long id);

    Utilisateur saveSecretaire(Utilisateur utilisateur);
    List<Utilisateur> getAllSecretaires();

    Optional<Utilisateur> updateSecretaire(Long id, Utilisateur utilisateur);

    boolean deleteSecretaire(Long id);
}
