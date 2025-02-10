package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.exceptions.PatientNotFoundException;
import com.miniProjet.kinesitherapie.model.dto.*;
import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.repositories.*;
import com.miniProjet.kinesitherapie.services.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final FicheMedicalRepository ficheMedicalRepository;
    private final RendezVousRepository rendezVousRepository;
    private final PaiementRepository paiementRepository;
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;


    @Override
    public PatientDTO createPatient(CreatePatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        patientRepository.save(patient);
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));

        modelMapper.map(patientDTO, existingPatient);
        Patient updatedPatient = patientRepository.save(existingPatient);
        return modelMapper.map(updatedPatient, PatientDTO.class);
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public Page<PatientDTO> getAllPatients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patients = patientRepository.findAll(pageable);
        return patients.map(patient -> modelMapper.map(patient, PatientDTO.class));
    }

    @Override
    public Page<PatientDTO> getAllPatientsSorted(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Patient> patients = patientRepository.findAll(pageable);
        return patients.map(patient -> modelMapper.map(patient, PatientDTO.class));
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public PatientHistoryDTO getPatientHistory(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));

        List<FicheMedicalDTO> fichesMedicales = ficheMedicalRepository.findByPatient_Id(patientId)
                .stream()
                .map(ficheMedical -> modelMapper.map(ficheMedical, FicheMedicalDTO.class))
                .toList();

        List<RendezVousDTO> rendezVous = rendezVousRepository.findByPatient_Id(patientId)
                .stream()
                .map(rdv -> modelMapper.map(rdv, RendezVousDTO.class))
                .toList();

        List<PaiementDTO> paiements = paiementRepository.findByPatient_Id(patientId)
                .stream()
                .map(paiement -> modelMapper.map(paiement, PaiementDTO.class))
                .toList();

        List<NotificationDTO> notifications = notificationRepository.findByPatient_Id(patientId)
                .stream()
                .map(notification -> modelMapper.map(notification, NotificationDTO.class))
                .toList();

        PatientHistoryDTO patientHistoryDTO = modelMapper.map(patient, PatientHistoryDTO.class);
        patientHistoryDTO.setFichesMedicales(fichesMedicales);
        patientHistoryDTO.setRendezVous(rendezVous);
        patientHistoryDTO.setPaiements(paiements);
        patientHistoryDTO.setNotifications(notifications);

        return patientHistoryDTO;
    }
}
