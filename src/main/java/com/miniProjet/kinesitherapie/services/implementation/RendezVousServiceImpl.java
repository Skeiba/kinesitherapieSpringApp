package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.exceptions.RendezVousNotFoundException;
import com.miniProjet.kinesitherapie.exceptions.SalleNotAvailableException;
import com.miniProjet.kinesitherapie.exceptions.SalleNotFoundException;
import com.miniProjet.kinesitherapie.model.dto.*;
import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.entities.Prestation;
import com.miniProjet.kinesitherapie.model.entities.RendezVous;
import com.miniProjet.kinesitherapie.model.entities.Salle;
import com.miniProjet.kinesitherapie.model.enums.RessourceStatus;
import com.miniProjet.kinesitherapie.model.enums.Statut;
import com.miniProjet.kinesitherapie.model.repositories.PrestationRepository;
import com.miniProjet.kinesitherapie.model.repositories.RendezVousRepository;
import com.miniProjet.kinesitherapie.model.repositories.SalleRepository;
import com.miniProjet.kinesitherapie.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RendezVousServiceImpl implements RendezVousService {
    private final RendezVousRepository rendezVousRepository;
    private final PatientService patientService;
    private final SalleRepository salleRepository;
    private final PrestationRepository prestationRepository;
    private final PaiementService paiementService;
    private final NotificationService notificationService;
    private final ModelMapper modelMapper;
    private final SalleService salleService;

    @Override
    public RendezVousDTO createRendezVous(CreateRendezVousDTO createRendezVousDTO) {
        log.info("Creating appointment for patient ID: {}", createRendezVousDTO.getPatientId());

        PatientDTO patientDTO = patientService.getPatientById(createRendezVousDTO.getPatientId());
        log.info("Patient found: {}", patientDTO.getNom());

        RendezVous rendezVous = createRendezVousEntity(createRendezVousDTO, patientDTO);
        Double totalAmount = paiementService.calculateTotalAmount(createRendezVousDTO.getPrestationIds());

        RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
        log.info("Appointment created with ID: {}", savedRendezVous.getId());

        paiementService.processPaiement(modelMapper.map(patientDTO, Patient.class), totalAmount);

        notificationService.sendRendezVousConfirmation(savedRendezVous, patientDTO, totalAmount);

        RendezVousDTO rendezVousDTO = modelMapper.map(savedRendezVous, RendezVousDTO.class);
        rendezVousDTO.setPrestationIds(
                savedRendezVous.getPrestations().stream().map(Prestation::getId).collect(Collectors.toList())
        );

        rendezVousDTO.setTotalAmount(totalAmount);
        return rendezVousDTO;
    }
    @Override
    public RendezVousDTO createRendezVousWithNewPatient(PatientRendezVousDTO patientRendezVousDTO) {
        PatientDTO patientDTO = patientService.createPatient(patientRendezVousDTO);

        CreateRendezVousDTO createRendezVousDTO = patientRendezVousDTO.getCreateRendezVousDTO();
        createRendezVousDTO.setPatientId(patientDTO.getId());

        return createRendezVous(createRendezVousDTO);
    }

    private RendezVous createRendezVousEntity(CreateRendezVousDTO createRendezVousDTO, PatientDTO patientDTO) {
        Salle salle = salleRepository.findById(createRendezVousDTO.getSalleId())
                .orElseThrow(() -> new SalleNotFoundException("Salle not found"));

        if (!isSalleAvailable(createRendezVousDTO.getSalleId(), createRendezVousDTO.getDateHeure())) {
            throw new SalleNotAvailableException("Salle is not available at that time");
        }

        RendezVous rendezVous = new RendezVous();
        rendezVous.setSalle(salle);
        rendezVous.setPatient(modelMapper.map(patientDTO, Patient.class));
        rendezVous.setDateHeure(createRendezVousDTO.getDateHeure());
        rendezVous.setStatus(Statut.EN_ATTENTE);
        //not tested yet
        salle.setStatus(RessourceStatus.IN_USE);
        salleService.updateSalle(salle.getId(), modelMapper.map(salle, SalleDTO.class));

//        if (createRendezVousDTO.getPrestationIds() != null) {
//            List<Prestation> prestations = prestationRepository.findAllById(createRendezVousDTO.getPrestationIds());
//
//            rendezVous.setPrestations(prestations);
//        }
        if (createRendezVousDTO.getPrestationIds() != null && !createRendezVousDTO.getPrestationIds().isEmpty()) {
            List<Prestation> prestations = prestationRepository.findAllById(createRendezVousDTO.getPrestationIds());
            rendezVous.setPrestations(prestations);
        } else {
            rendezVous.setPrestations(new ArrayList<>()); // Évite le null
        }

        log.info(rendezVous.getPrestations().toString());
        return rendezVous;
    }

    @Override
    public boolean isSalleAvailable(Long salleId, LocalDateTime dateHeure) {
        Salle salle = salleRepository.findById(salleId)
                .orElseThrow(() -> new SalleNotFoundException("Salle not found"));

        if (!salle.getStatus().equals(RessourceStatus.AVAILABLE)){
            return false;
        }
        boolean isBooked = rendezVousRepository.existsBySalle_IdAndDateHeureBetween(
                salleId,
                dateHeure.minusMinutes(30),
                dateHeure.plusMinutes(30)
        );
        return !isBooked;
    }

    @Override
    public RendezVousDTO updateRendezVous(Long rdvId, RendezVousUpdateDTO dto) {
        if (dto == null || rdvId == null) {
            throw new IllegalArgumentException("Rendez vous cannot be null");
        }
        RendezVousDTO rendezVous = getRendezVous(rdvId);

        if (!isSalleAvailable(dto.getSalleId(), dto.getDateHeure())){
            throw new SalleNotAvailableException("Salle is not available at that time");
        }

        if (dto.getStatus() != null){
            rendezVous.setStatus(dto.getStatus());
        }
        if (dto.getDateHeure() != null){
            rendezVous.setDateHeure(dto.getDateHeure());
        }
        if (dto.getSalleId() != null){
            rendezVous.setSalleId(dto.getSalleId());
        }
        if (dto.getPrestationIds() != null){
            rendezVous.setPrestationIds(dto.getPrestationIds());
        }
        RendezVous updatedRdv = modelMapper.map(rendezVous, RendezVous.class);
        rendezVousRepository.save(updatedRdv);
        return rendezVous;
    }

    @Override
    public boolean deleteRendezVous(Long id) {
        Optional<RendezVous> rendezVous = rendezVousRepository.findById(id);
        if (rendezVous.isPresent()) {
            rendezVousRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public RendezVousDTO getRendezVous(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id).
                orElseThrow(() -> new RendezVousNotFoundException("No rendez vous with this Id"));
        return modelMapper.map(rendezVous, RendezVousDTO.class);
    }

    @Override
    public Page<RendezVousDTO> getAllRendezVous(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RendezVous> rendezVous = rendezVousRepository.findAll(pageable);
        return rendezVous.map(rdv -> modelMapper.map(rdv, RendezVousDTO.class));
    }
}
