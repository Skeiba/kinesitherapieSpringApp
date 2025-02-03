package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.model.enums.Statut;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendezVousDTO {
    @Positive
    private Long id;

    @NotBlank(message = AppConstants.INVALID_DATE)
    private LocalDateTime dateHeure;

    private Statut status;
}
