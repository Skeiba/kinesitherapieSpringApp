package com.miniProjet.kinesitherapie.model.repository;

import com.miniProjet.kinesitherapie.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
