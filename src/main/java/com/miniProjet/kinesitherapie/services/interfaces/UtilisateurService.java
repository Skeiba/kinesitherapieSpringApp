package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.auth.dto.RegisterRequest;
import com.miniProjet.kinesitherapie.exceptions.EmailAlreadyExistsException;
import com.miniProjet.kinesitherapie.model.dto.UserUpdateDTO;
import com.miniProjet.kinesitherapie.model.dto.UtilisateurDTO;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService extends UserDetailsService {
    Utilisateur registerUtilisateur(RegisterRequest registerRequest) throws EmailAlreadyExistsException;
    Utilisateur findByEmail(String email);
    UtilisateurDTO updateUtilisateur(Long userId, UserUpdateDTO updateRequest);
    UtilisateurDTO getUtilisateur(Long userId);
    Page<UtilisateurDTO> getAllUtilisateurs(int page, int size);
    void updateLoggedInStatus(Long id, boolean isLoggedIn);
    boolean removeUtilisateur(Long id);
    void saveUtilisateur(Utilisateur utilisateur);
    boolean existsByEmail(String email);
    Optional<Utilisateur> findById(Long id);
    Utilisateur saveSecretaire(Utilisateur utilisateur);
    List<Utilisateur> getAllSecretaires();
    Optional<Utilisateur> updateSecretaire(Long id, Utilisateur utilisateur);
    boolean deleteSecretaire(Long id);
}
