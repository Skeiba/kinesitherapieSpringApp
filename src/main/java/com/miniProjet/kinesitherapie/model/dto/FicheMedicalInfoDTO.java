package com.miniProjet.kinesitherapie.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FicheMedicalInfoDTO {
    @Positive
    private Long id;

    @NotBlank
    private String description;
}
