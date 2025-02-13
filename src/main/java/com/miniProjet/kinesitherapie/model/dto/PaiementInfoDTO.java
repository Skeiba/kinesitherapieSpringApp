package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementInfoDTO {
    @Positive
    private Long id;

    @NotNull(message = "Montant can't be null")
    private double montant;

    @NotBlank(message = AppConstants.INVALID_DATE)
    private LocalDateTime date;
}
