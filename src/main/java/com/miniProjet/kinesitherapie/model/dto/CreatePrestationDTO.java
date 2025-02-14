package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.model.enums.PrestationType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePrestationDTO {
    private String nom;
    private PrestationType type;
    private double tarif;
}
