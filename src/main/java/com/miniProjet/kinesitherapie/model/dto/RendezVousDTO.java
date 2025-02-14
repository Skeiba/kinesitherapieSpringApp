package com.miniProjet.kinesitherapie.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RendezVousDTO extends RendezVousInfoDTO{
    private Long patientId;
    private List<Long> prestationIds;
    private Double totalAmount;
}
