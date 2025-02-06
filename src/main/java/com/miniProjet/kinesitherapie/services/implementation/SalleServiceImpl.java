package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.entities.Salle;
import com.miniProjet.kinesitherapie.model.enums.RessourceStatus;
import com.miniProjet.kinesitherapie.model.repositories.SalleRepository;
import com.miniProjet.kinesitherapie.services.interfaces.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalleServiceImpl  implements SalleService {

    @Autowired
    private SalleRepository salleRepository;

    public Salle createSalle(Salle salle) {
        return salleRepository.save(salle);
    }


    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public Salle getSalleById(Long id) {
        return salleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salle not found with id: " + id));
    }


    public Salle updateSalle(Long id, Salle salleDetails) {
        Salle existingSalle = getSalleById(id);
        existingSalle.setNom(salleDetails.getNom());
        existingSalle.setNombreLits(salleDetails.getNombreLits());
        existingSalle.setLocation(salleDetails.getLocation());
        existingSalle.setStatus(salleDetails.getStatus());
        return salleRepository.save(existingSalle);
    }

    public void deleteSalle(Long id) {
        Salle salle = getSalleById(id);
        salleRepository.delete(salle);
    }

    @Override
    public List<Salle> getAvailableSalles() {
        List<Salle> salleList = salleRepository.findAll();
        List<Salle> availableSalles = new ArrayList<>();
        for (Salle salle : salleList) {
            if (salle.getStatus() == RessourceStatus.AVAILABLE) {
                availableSalles.add(salle);
            }
        }
        return availableSalles;
    }

    @Override
    public void setPatientSalle(Long id, Patient patient) {
        Salle salle = getSalleById(id);
        salle.setStatus(RessourceStatus.OCCUPIED);
        salleRepository.save(salle);
    }
}
