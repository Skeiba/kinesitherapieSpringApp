package com.miniProjet.kinesitherapie.services.interfaces;

import com.miniProjet.kinesitherapie.model.dto.CreatePatientDTO;
import com.miniProjet.kinesitherapie.model.dto.PatientDTO;
import com.miniProjet.kinesitherapie.model.dto.PatientHistoryDTO;
import org.springframework.data.domain.Page;

public interface PatientService {
    PatientDTO createPatient(CreatePatientDTO patientDTO);
    PatientDTO updatePatient(Long id, PatientDTO patientDTO);
    PatientDTO getPatientById(Long id);
    Page<PatientDTO> getAllPatients(int page, int size);
    Page<PatientDTO> getAllPatientsSorted(int page, int size, String sortBy, String direction);
    void deletePatient(Long id);
    PatientHistoryDTO getPatientHistory(Long patientId);
}
