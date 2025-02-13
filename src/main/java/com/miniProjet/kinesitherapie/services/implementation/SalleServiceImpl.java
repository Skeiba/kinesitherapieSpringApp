package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.model.dto.SalleDTO;
import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.entities.Salle;
import com.miniProjet.kinesitherapie.model.enums.RessourceStatus;
import com.miniProjet.kinesitherapie.model.repositories.SalleRepository;
import com.miniProjet.kinesitherapie.services.interfaces.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalleServiceImpl implements SalleService {

    @Autowired
    private SalleRepository salleRepository;

    public SalleDTO createSalle(SalleDTO salleDTO) {
        Salle salle = convertToEntity(salleDTO);
        salle = salleRepository.save(salle);
        return convertToDTO(salle);
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
        if (salleDetails.getStatus() != null) { // Ensure status is not null
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
        dto.setQueue(0); // Example logic
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
