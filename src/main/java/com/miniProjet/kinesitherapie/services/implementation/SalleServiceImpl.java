package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.model.dto.CreateSalleDTO;
import com.miniProjet.kinesitherapie.model.dto.SalleDTO;
import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.entities.Salle;
import com.miniProjet.kinesitherapie.model.enums.RessourceStatus;
import com.miniProjet.kinesitherapie.model.repositories.SalleRepository;
import com.miniProjet.kinesitherapie.services.interfaces.SalleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SalleServiceImpl implements SalleService {

    private final SalleRepository salleRepository;
    private final ModelMapper modelMapper;

    public SalleDTO createSalle(CreateSalleDTO salleDTO) {
        Salle salle = modelMapper.map(salleDTO, Salle.class);
        salleRepository.save(salle);
        return modelMapper.map(salle, SalleDTO.class);
    }

    public List<SalleDTO> getAllSalles() {
        return salleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SalleDTO getSalleById(Long id) {
        Salle salle = salleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salle not found with id: " + id));
        return convertToDTO(salle);
    }

    public SalleDTO updateSalle(Long id, SalleDTO salleDetails) {
        Salle existingSalle = salleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salle not found with id: " + id));

        existingSalle.setNom(salleDetails.getName());
        existingSalle.setNombreLits(salleDetails.getCapacity());
        existingSalle.setLocation(salleDetails.getLocation());
        if (salleDetails.getStatus() != null) {
            existingSalle.setStatus(salleDetails.getStatus());
        }

        salleRepository.save(existingSalle);
        return convertToDTO(existingSalle);
    }

    public void deleteSalle(Long id) {
        Salle salle = salleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salle not found with id: " + id));
        salleRepository.delete(salle);
    }

    @Override
    public List<SalleDTO> getAvailableSalles() {
        return salleRepository.findAll()
                .stream()
                .filter(salle -> salle.getStatus() == RessourceStatus.AVAILABLE)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void setPatientSalle(Long id, Patient patient) {
        Salle salle = salleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salle not found with id: " + id));
        salle.setStatus(RessourceStatus.OCCUPIED);
        salleRepository.save(salle);
    }

    // 🔹 Convert Entity -> DTO
    private SalleDTO convertToDTO(Salle salle) {
        SalleDTO dto = new SalleDTO();
        dto.setId(salle.getId());
        dto.setName(salle.getNom());
        dto.setLocation(salle.getLocation());
        dto.setCapacity(salle.getNombreLits());
        dto.setStatus(RessourceStatus.valueOf(salle.getStatus().name()));
        return dto;
    }

    // 🔹 Convert DTO -> Entity
    private Salle convertToEntity(SalleDTO dto) {
        Salle salle = new Salle();
        salle.setNom(dto.getName());
        salle.setLocation(dto.getLocation());
        salle.setNombreLits(dto.getCapacity());
        salle.setStatus(RessourceStatus.valueOf(String.valueOf(dto.getStatus())));
        return salle;
    }
}
