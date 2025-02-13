package com.miniProjet.kinesitherapie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientHistoryDTO extends PatientDTO{
    private List<FicheMedicalInfoDTO> fichesMedicales;
    private List<RendezVousInfoDTO> rendezVous;
    private List<PaiementInfoDTO> paiements;
    private List<NotificationInfoDTO> notifications;
}
