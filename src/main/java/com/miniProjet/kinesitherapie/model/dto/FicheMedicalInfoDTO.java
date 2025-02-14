package com.miniProjet.kinesitherapie.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FicheMedicalInfoDTO {
    @Positive
    private Long id;

    private LocalDateTime dateHeure;

    @NotBlank
    private String description;
}
