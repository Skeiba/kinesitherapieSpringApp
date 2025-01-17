package com.miniProjet.kinesitherapie.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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

    @Column(nullable = false) @Temporal(TemporalType.DATE)
    private LocalDate dateGeneration;

    @OneToMany(mappedBy = "statistiques", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RendezVous> rendezVous;

    @OneToMany(mappedBy = "statistiques", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiements;

    @OneToMany(mappedBy = "statistiques", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestation> prestations;
}
