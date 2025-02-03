package com.miniProjet.kinesitherapie.model.repositories;

import com.miniProjet.kinesitherapie.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByPatient_Id(Long patientId);
}
