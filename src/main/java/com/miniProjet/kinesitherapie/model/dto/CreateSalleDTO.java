package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.model.enums.RessourceStatus;
import lombok.Data;

@Data
public class CreateSalleDTO {
    private String nom;
    private String location;
    private RessourceStatus status;
    private int nombreLits;
}
