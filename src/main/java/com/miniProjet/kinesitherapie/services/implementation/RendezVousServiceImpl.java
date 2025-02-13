package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.exceptions.RendezVousNotFoundException;
import com.miniProjet.kinesitherapie.exceptions.SalleNotAvailableException;
import com.miniProjet.kinesitherapie.model.dto.PatientDTO;
import com.miniProjet.kinesitherapie.model.dto.PatientRendezVousDTO;
import com.miniProjet.kinesitherapie.model.dto.RendezVousDTO;
import com.miniProjet.kinesitherapie.model.dto.RendezVousUpdateDTO;
import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.entities.Prestation;
import com.miniProjet.kinesitherapie.model.entities.RendezVous;
import com.miniProjet.kinesitherapie.model.entities.Salle;
import com.miniProjet.kinesitherapie.model.repositories.PrestationRepository;
import com.miniProjet.kinesitherapie.model.repositories.RendezVousRepository;
import com.miniProjet.kinesitherapie.model.repositories.SalleRepository;
import com.miniProjet.kinesitherapie.services.interfaces.PatientService;
import com.miniProjet.kinesitherapie.services.interfaces.RendezVousService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RendezVousServiceImpl implements RendezVousService {
    private final RendezVousRepository rendezVousRepository;
    private final PatientService patientService;
    private final SalleRepository salleRepository;
    private final PrestationRepository prestationRepository;
    private final ModelMapper modelMapper;

    @Override
    public RendezVousDTO createRendezVous(RendezVousDTO rendezVousDTO) {
        PatientDTO patientDTO = patientService.getPatientById(rendezVousDTO.getPatientId());
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        Salle salle = salleRepository.findById(rendezVousDTO.getSalleId())
                .orElseThrow(() -> new IllegalArgumentException("Salle not found"));

        if (!isSalleAvailable(rendezVousDTO.getSalleId(), rendezVousDTO.getDateHeure())){
            throw new SalleNotAvailableException("Salle is not available at that time");
        }

        RendezVous rendezVous = new RendezVous();
        rendezVous.setSalle(salle);
        rendezVous.setPatient(patient);
        rendezVous.setDateHeure(rendezVousDTO.getDateHeure());
        rendezVous.setStatus(rendezVousDTO.getStatus());

        if (rendezVousDTO.getPrestationIds() != null) {
            List<Prestation> prestations = prestationRepository.findAllById(rendezVousDTO.getPrestationIds());
            rendezVous.setPrestations(prestations);
        }
        RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
        return modelMapper.map(savedRendezVous, RendezVousDTO.class);
    }

    @Override
    public RendezVousDTO createRendezVousWithNewPatient(PatientRendezVousDTO patientRendezVousDTO) {
        PatientDTO patientDTO = patientService.createPatient(patientRendezVousDTO);

        RendezVousDTO rendezVousDTO = patientRendezVousDTO.getRendezVousDTO();
        rendezVousDTO.setPatientId(patientDTO.getId());

        return createRendezVous(rendezVousDTO);
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

    @Override
    public boolean isSalleAvailable(Long salleId, LocalDateTime dateHeure) {
        return !rendezVousRepository.existsBySalle_IdAndDateHeureBetween(
                salleId,
                dateHeure.minusMinutes(30),
                dateHeure.plusMinutes(30)
        );
    }
}
