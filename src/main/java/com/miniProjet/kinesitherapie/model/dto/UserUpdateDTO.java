package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.model.enums.Role;
import lombok.Data;

@Data
public class UserUpdateDTO {
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
}
