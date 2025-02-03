package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.auth.dto.RegisterRequest;
import com.miniProjet.kinesitherapie.exceptions.EmailAlreadyExistsException;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UtilisateurService extends UserDetailsService {
    void saveUtilisateur(Utilisateur utilisateur);
    Utilisateur registerUtilisateur(RegisterRequest registerRequest) throws EmailAlreadyExistsException;
    Utilisateur findByEmail(String email);
    boolean existsByEmail(String email);
    void updateLoggedInStatus(Long id, boolean isLoggedIn);
}
