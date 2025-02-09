package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.dto.SalleDTO;
import com.miniProjet.kinesitherapie.model.entities.Patient;

import java.util.List;

public interface SalleService {

    SalleDTO createSalle(SalleDTO salleDTO);

    List<SalleDTO> getAllSalles();

    SalleDTO getSalleById(Long id);

    SalleDTO updateSalle(Long id, SalleDTO salleDetails);

    void deleteSalle(Long id);

    List<SalleDTO> getAvailableSalles();

    void setPatientSalle(Long id, Patient patient);
}
