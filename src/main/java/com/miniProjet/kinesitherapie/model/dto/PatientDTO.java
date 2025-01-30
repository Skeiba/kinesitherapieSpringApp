package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    @NotNull(message = AppConstants.INVALID_NOM)
    private String nom;

    @NotNull(message = AppConstants.INVALID_PRENOM)
    private String prenom;

    @NotNull(message = "Email cannot be null")
    @Email(message = AppConstants.INVALID_EMAIL)
    private String email;

    @NotNull(message = AppConstants.INVALID_ADRESSE)
    private String adresse;

    @Pattern(regexp = "^(06|07|05)[0-9]{8}$", message = AppConstants.INVALID_PHONE_NUMBER)
    private String telephone;
}
