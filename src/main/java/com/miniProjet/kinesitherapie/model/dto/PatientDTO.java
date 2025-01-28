package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    @NotNull(message = "Nom is required")
    private String nom;

    @NotNull(message = "Prenom is required")
    private String prenom;

    @NotNull(message = "Adresse is required")
    private String adresse;

    @Pattern(regexp = "^(06|07|05)[0-9]{8}$", message = AppConstants.INVALID_PHONE_NUMBER)
    private String telephone;
}
