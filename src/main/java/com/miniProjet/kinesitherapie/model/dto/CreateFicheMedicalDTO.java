package com.miniProjet.kinesitherapie.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFicheMedicalDTO {
    private Long patientId;
    @NotBlank
    private String description;
}
