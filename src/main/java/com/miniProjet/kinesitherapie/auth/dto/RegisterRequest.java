package com.miniProjet.kinesitherapie.auth.dto;

import com.miniProjet.kinesitherapie.model.enums.Role;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = AppConstants.INVALID_NOM)
    private String nom;

    @NotBlank(message = AppConstants.INVALID_PRENOM)
    private String prenom;

    @NotBlank(message = "Email can't be empty")
    @Email(message = AppConstants.INVALID_EMAIL)
    private String email;

    @NotBlank(message = "Mot de paase can't be empty")
    private String motDePasse;

    @NotBlank
    private Role role;

    public Role getRoleEnum() {
        try {
            return Role.valueOf(role.toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role value");
        }
    }

}