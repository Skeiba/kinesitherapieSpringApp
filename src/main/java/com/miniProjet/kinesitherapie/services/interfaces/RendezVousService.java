package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.dto.CreateRendezVousDTO;
import com.miniProjet.kinesitherapie.model.dto.PatientRendezVousDTO;
import com.miniProjet.kinesitherapie.model.dto.RendezVousDTO;
import com.miniProjet.kinesitherapie.model.dto.RendezVousUpdateDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface RendezVousService {
    RendezVousDTO createRendezVous(CreateRendezVousDTO createRendezVousDTO);
    RendezVousDTO createRendezVousWithNewPatient(PatientRendezVousDTO patientRendezVousDTO);
    RendezVousDTO updateRendezVous(Long rdvId, RendezVousUpdateDTO dto);
    boolean deleteRendezVous(Long id);
    RendezVousDTO getRendezVous(Long id);
    Page<RendezVousDTO> getAllRendezVous(int page, int size);
    boolean isSalleAvailable(Long salleId, LocalDateTime dateHeure);
}
