package com.miniProjet.kinesitherapie.model.entity;

import java.time.LocalDateTime;

public class Paiement {
    private Long id;
    private Patient patient;
    private double montant;
    private LocalDateTime date;
}
