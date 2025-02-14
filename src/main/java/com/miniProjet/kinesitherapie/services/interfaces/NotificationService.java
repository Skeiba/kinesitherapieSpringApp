package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.dto.PatientDTO;
import com.miniProjet.kinesitherapie.model.entities.RendezVous;

public interface NotificationService {
    void sendRendezVousConfirmation(RendezVous savedRendezVous, PatientDTO patientDTO, Double totalAmount);

}
