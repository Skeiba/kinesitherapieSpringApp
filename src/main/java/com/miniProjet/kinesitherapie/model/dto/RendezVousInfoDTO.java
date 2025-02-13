package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.model.enums.Statut;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RendezVousInfoDTO {
    @Positive
    private Long id;

    @NotBlank(message = AppConstants.INVALID_DATE)
    private LocalDateTime dateHeure;

    private Long salleId;

    private Statut status;
}
