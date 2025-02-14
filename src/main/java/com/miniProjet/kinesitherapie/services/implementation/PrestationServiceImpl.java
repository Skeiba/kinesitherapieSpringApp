package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.model.dto.CreatePrestationDTO;
import com.miniProjet.kinesitherapie.model.dto.PrestationDTO;
import com.miniProjet.kinesitherapie.model.entities.Prestation;
import com.miniProjet.kinesitherapie.model.repositories.PrestationRepository;
import com.miniProjet.kinesitherapie.services.interfaces.PrestationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PrestationServiceImpl implements PrestationService {
    private final PrestationRepository prestationRepository;
    private final ModelMapper modelMapper;

    @Override
    public PrestationDTO createPrestation(CreatePrestationDTO prestationDTO) {
        Prestation prestation = modelMapper.map(prestationDTO, Prestation.class);
        prestationRepository.save(prestation);
        return modelMapper.map(prestation, PrestationDTO.class);
    }

    @Override
    public List<PrestationDTO> getAllPrestations() {
        List<Prestation> prestations = prestationRepository.findAll();
        return prestations.stream().map(p -> modelMapper.map(p, PrestationDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PrestationDTO getPrestationById(Long id) {
        Prestation prestation = prestationRepository.findById(id).orElse(null);
        return prestation == null ? null : modelMapper.map(prestation, PrestationDTO.class);
    }

    @Override
    public PrestationDTO updatePrestation(Long id, PrestationDTO prestationDTO) {
        return null;
    }

    @Override
    public boolean deletePrestation(Long id) {
        if (prestationRepository.existsById(id)) {
            prestationRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
