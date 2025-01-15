package com.miniProjet.kinesitherapie.model.entity;

import com.miniProjet.kinesitherapie.model.entity.enums.Statut;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "rendezVous")
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private LocalDateTime dateHeure;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut status;

    @ManyToOne
    @JoinColumn(name = "salle_id", nullable = false)
    private Salle salle;

    @ManyToMany
    @JoinTable(
            name = "rendezvous_prestation",
            joinColumns = @JoinColumn(name = "rendezvous_id"),
            inverseJoinColumns = @JoinColumn(name = "prestation_id")
    )
    private List<Prestation> prestations;

    @ManyToOne
    @JoinColumn(name = "statistiques_id")
    private Statistiques statistiques;
}
