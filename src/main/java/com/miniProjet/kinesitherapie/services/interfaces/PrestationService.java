package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.dto.CreatePrestationDTO;
import com.miniProjet.kinesitherapie.model.dto.PrestationDTO;

import java.util.List;

public interface PrestationService {
    PrestationDTO createPrestation(CreatePrestationDTO prestationDTO);
    List<PrestationDTO> getAllPrestations();
    PrestationDTO getPrestationById(Long id);
    PrestationDTO updatePrestation(Long id, PrestationDTO prestationDTO);
    boolean deletePrestation(Long id);
}
