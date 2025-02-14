package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.dto.CreateFicheMedicalDTO;
import com.miniProjet.kinesitherapie.model.dto.FicheMedicalInfoDTO;

import java.util.List;

public interface FicheMedicalService {
    FicheMedicalInfoDTO createFicheMedical(CreateFicheMedicalDTO ficheMedicalDTO);
    List<FicheMedicalInfoDTO> getAllFicheMedical(Long patientId);
}
