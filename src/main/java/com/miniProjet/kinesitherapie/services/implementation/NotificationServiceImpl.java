package com.miniProjet.kinesitherapie.services.implementation;

import com.miniProjet.kinesitherapie.model.dto.PatientDTO;
import com.miniProjet.kinesitherapie.model.entities.Notification;
import com.miniProjet.kinesitherapie.model.entities.Patient;
import com.miniProjet.kinesitherapie.model.entities.RendezVous;
import com.miniProjet.kinesitherapie.model.repositories.NotificationRepository;
import com.miniProjet.kinesitherapie.services.interfaces.NotificationService;
import com.miniProjet.kinesitherapie.utils.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final EmailSenderService emailSenderService;
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    @Override
    public void sendRendezVousConfirmation(RendezVous savedRendezVous, PatientDTO patientDTO, Double totalAmount) {
        String subject = "Rendez-vous Confirmation";
        String message = String.format(
                "Dear %s, your rendez-vous is confirmed for %s. Total amount: %.2f DH",
                patientDTO.getNom(),
                savedRendezVous.getDateHeure(),
                totalAmount
        );

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDateEnvoi(LocalDate.now());
        notification.setPatient(modelMapper.map(patientDTO, Patient.class));
        notificationRepository.save(notification);

        emailSenderService.sendNotificationToPatient(patientDTO.getEmail(), subject, message);
    }
}
