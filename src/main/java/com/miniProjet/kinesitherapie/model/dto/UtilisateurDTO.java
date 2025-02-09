package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.model.enums.Role;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UtilisateurDTO {
    @Positive
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
}
