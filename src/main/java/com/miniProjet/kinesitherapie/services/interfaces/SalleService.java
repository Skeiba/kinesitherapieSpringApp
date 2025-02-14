package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.dto.CreateSalleDTO;
import com.miniProjet.kinesitherapie.model.dto.SalleDTO;
import com.miniProjet.kinesitherapie.model.entities.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SalleService {

    SalleDTO createSalle(CreateSalleDTO salleDTO);

    Page<SalleDTO> getAllSalles(int page, int size);

    SalleDTO getSalleById(Long id);

    SalleDTO updateSalle(Long id, SalleDTO salleDetails);

    void deleteSalle(Long id);

    List<SalleDTO> getAvailableSalles();

    void setPatientSalle(Long id, Patient patient);
    Long countAllSalles();
}
