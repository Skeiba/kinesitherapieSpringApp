package com.miniProjet.kinesitherapie.auth.dto;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email can't be empty")
        @Email(message = AppConstants.INVALID_EMAIL)
        String email,

        @NotBlank(message = "Mot de passe can't be empty")
        String motDePasse
) {}