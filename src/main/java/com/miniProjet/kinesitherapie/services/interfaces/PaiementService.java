package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.entities.RendezVous;

import java.util.List;

public interface PaiementService {
    Double calculateTotalAmount(List<Long> prestationIds);
    void processPaiement(Patient patient, Double amount);
}
