package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreatePatientDTO {
    @NotBlank(message = AppConstants.INVALID_NOM)
    private String nom;

    @NotBlank(message = AppConstants.INVALID_PRENOM)
    private String prenom;

    @NotNull(message = "Email cannot be null")
    @Email(message = AppConstants.INVALID_EMAIL)
    private String email;

    @NotBlank(message = AppConstants.INVALID_ADRESSE)
    private String adresse;

    @NotBlank(message = "Telephone cannot be blank")
    @Pattern(regexp = "^(06|07|05)[0-9]{8}$", message = AppConstants.INVALID_PHONE_NUMBER)
    private String telephone;
}
