package com.miniProjet.kinesitherapie.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "statistiques")
public class Statistiques {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rapport;

    @Column(nullable = false)
    private LocalDateTime dateGeneration;

    @OneToMany(mappedBy = "statistiques", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RendezVous> rendezVous;

    @OneToMany(mappedBy = "statistiques", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiements;

    @OneToMany(mappedBy = "statistiques", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestation> prestations;
}
