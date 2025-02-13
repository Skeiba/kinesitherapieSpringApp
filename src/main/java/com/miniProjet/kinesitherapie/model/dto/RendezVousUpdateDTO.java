package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.model.enums.Statut;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class RendezVousUpdateDTO {
    private LocalDateTime dateHeure;
    private Long salleId;
    private Statut status;
    private List<Long> prestationIds;
}
