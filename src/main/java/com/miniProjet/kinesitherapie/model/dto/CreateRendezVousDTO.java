package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.model.enums.Statut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRendezVousDTO {
    private Long patientId;
    private Long salleId;
    private LocalDateTime dateHeure;
    private Statut status;
    private List<Long> prestationIds;

}
