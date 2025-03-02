package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.model.entities.Paiement;
import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.entities.Prestation;
import com.miniProjet.kinesitherapie.model.repositories.PaiementRepository;
import com.miniProjet.kinesitherapie.model.repositories.PrestationRepository;
import com.miniProjet.kinesitherapie.services.interfaces.PaiementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {
    private final PrestationRepository prestationRepository;
    private final PaiementRepository paiementRepository;
    @Override
    public Double calculateTotalAmount(List<Long> prestationIds) {
        if (prestationIds == null || prestationIds.isEmpty()) {
            return 0.0;
        }

        List<Prestation> prestations = prestationRepository.findAllById(prestationIds);
        return prestations.stream().mapToDouble(Prestation::getTarif).sum();
    }
    @Override
    public void processPaiement(Patient patient, Double amount) {
        if (amount > 0.0) {
            Paiement paiement = new Paiement();
            paiement.setMontant(amount);
            paiement.setDate(LocalDateTime.now());
            paiement.setPatient(patient);
            paiementRepository.save(paiement);
        }
    }
}
