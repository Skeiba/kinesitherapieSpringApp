package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.auth.dto.RegisterRequest;
import com.miniProjet.kinesitherapie.auth.model.CustomUserDetails;
import com.miniProjet.kinesitherapie.exceptions.EmailAlreadyExistsException;
import com.miniProjet.kinesitherapie.exceptions.EmailDontExistException;
import com.miniProjet.kinesitherapie.model.dto.UserUpdateDTO;
import com.miniProjet.kinesitherapie.model.dto.UtilisateurDTO;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import com.miniProjet.kinesitherapie.model.enums.Role;
import com.miniProjet.kinesitherapie.model.repositories.UtilisateurRepository;
import com.miniProjet.kinesitherapie.services.interfaces.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public void updateLoggedInStatus(Long id, boolean isLoggedIn) {
        utilisateurRepository.updateLoggedInStatus(id, isLoggedIn);
    }

    @Override
    public boolean removeUtilisateur(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isPresent()) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {

    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        return Optional.empty();
    }

    public Utilisateur saveSecretaire(Utilisateur utilisateur) {
        utilisateur.setRole(Role.SECRETAIRE);
        return  utilisateurRepository.save(utilisateur);
    }
    @Override
    public UtilisateurDTO updateUtilisateur(Long userId, UserUpdateDTO dto) {

        if (dto == null || userId == null) {
            throw new IllegalArgumentException("Utilisateur id and dto cannot be null");
        }

        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        if (dto.getEmail() != null && !dto.getEmail().equals(utilisateur.getEmail())
                && utilisateurRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        if (dto.getNom() != null) {
            utilisateur.setNom(dto.getNom());
        }
        if (dto.getPrenom() != null) {
            utilisateur.setPrenom(dto.getPrenom());
        }
        if (dto.getEmail() != null) {
            utilisateur.setEmail(dto.getEmail());
        }
        if (dto.getMotDePasse() != null && !dto.getMotDePasse().trim().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(dto.getMotDePasse());
            utilisateur.setMotDePasse(encodedPassword);
        }
        if (dto.getRole() != null) {
            utilisateur.setRole(dto.getRole());
        }
        Utilisateur updatedUtilisateur = utilisateurRepository.save(utilisateur);

        return modelMapper.map(updatedUtilisateur, UtilisateurDTO.class);
    }


    public List<Utilisateur> getAllSecretaires() {
        // Fetch all users with role SECRETAIRE
        List<Utilisateur> secretaires = utilisateurRepository.findAllByRole(Role.SECRETAIRE);
        return secretaires;
    }

    @Override
    public UtilisateurDTO getUtilisateur(Long userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        return modelMapper.map(utilisateur, UtilisateurDTO.class);
    }

    @Override
    public Page<UtilisateurDTO> getAllUtilisateurs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Utilisateur> utilisateurs = utilisateurRepository.findAll(pageable);
        return utilisateurs.map(utilisateur -> modelMapper.map(utilisateur, UtilisateurDTO.class));
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new EmailDontExistException("Utilisateur not found with email :"+email));
    }


    public Optional<Utilisateur> updateSecretaire(Long id, Utilisateur utilisateur) {
        if (utilisateurRepository.existsById(id)) {
            utilisateur.setId(id);
            utilisateur.setRole(Role.SECRETAIRE);
            return Optional.of(utilisateurRepository.save(utilisateur));
        }
        return Optional.empty();
    }

    public boolean deleteSecretaire(Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
