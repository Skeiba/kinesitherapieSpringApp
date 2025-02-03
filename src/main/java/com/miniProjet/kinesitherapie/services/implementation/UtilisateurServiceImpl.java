package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.auth.dto.RegisterRequest;
import com.miniProjet.kinesitherapie.auth.model.CustomUserDetails;
import com.miniProjet.kinesitherapie.exceptions.EmailAlreadyExistsException;
import com.miniProjet.kinesitherapie.exceptions.EmailDontExistException;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import com.miniProjet.kinesitherapie.model.repositories.UtilisateurRepository;
import com.miniProjet.kinesitherapie.services.interfaces.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new EmailDontExistException("Email not found"));
        return new CustomUserDetails(utilisateur.getEmail(),utilisateur.getMotDePasse(),utilisateur.getRole());
    }

    @Override
    public Utilisateur registerUtilisateur(RegisterRequest registerRequest) throws EmailAlreadyExistsException {
        if (utilisateurRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Utilisateur utilisateur = modelMapper.map(registerRequest, Utilisateur.class);
        utilisateur.setMotDePasse(passwordEncoder.encode(registerRequest.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }
    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public boolean existsByEmail(String email) {
        return utilisateurRepository.existsByEmail(email);
    }

    @Override
    public void updateLoggedInStatus(Long id, boolean isLoggedIn) {
        utilisateurRepository.updateLoggedInStatus(id, isLoggedIn);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new EmailDontExistException("Utilisateur not found with email :"+email));
    }

}
