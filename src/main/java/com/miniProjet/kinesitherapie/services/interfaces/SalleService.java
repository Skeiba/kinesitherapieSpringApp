package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.entities.Salle;
import com.miniProjet.kinesitherapie.model.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalleService {

    Salle createSalle(Salle salle);


    List<Salle> getAllSalles();

    Salle getSalleById(Long id) ;


    Salle updateSalle(Long id, Salle salleDetails) ;


    void deleteSalle(Long id) ;

    List<Salle> getAvailableSalles();

    void setPatientSalle(Long id, Patient patient);


}

