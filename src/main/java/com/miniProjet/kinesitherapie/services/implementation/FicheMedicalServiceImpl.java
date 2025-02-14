package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.exceptions.PatientNotFoundException;
import com.miniProjet.kinesitherapie.model.dto.CreateFicheMedicalDTO;
import com.miniProjet.kinesitherapie.model.dto.FicheMedicalInfoDTO;
import com.miniProjet.kinesitherapie.model.dto.PatientDTO;
import com.miniProjet.kinesitherapie.model.entities.FicheMedical;
import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.repositories.FicheMedicalRepository;
import com.miniProjet.kinesitherapie.services.interfaces.FicheMedicalService;
import com.miniProjet.kinesitherapie.services.interfaces.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FicheMedicalServiceImpl implements FicheMedicalService {
    private final FicheMedicalRepository ficheMedicalRepository;
    private final ModelMapper modelMapper;

    @Override
    public FicheMedicalInfoDTO createFicheMedical(CreateFicheMedicalDTO ficheMedicalDTO) {
        FicheMedical ficheMedical = modelMapper.map(ficheMedicalDTO, FicheMedical.class);
        ficheMedical.setDateHeure(LocalDateTime.now());
        ficheMedicalRepository.save(ficheMedical);
        return modelMapper.map(ficheMedical, FicheMedicalInfoDTO.class);
    }

    @Override
    public List<FicheMedicalInfoDTO> getAllFicheMedical(Long patientId) {
        return ficheMedicalRepository.findByPatient_Id(patientId)
                .stream()
                .map(fiche -> modelMapper.map(fiche, FicheMedicalInfoDTO.class))
                .collect(Collectors.toList());
    }
}
