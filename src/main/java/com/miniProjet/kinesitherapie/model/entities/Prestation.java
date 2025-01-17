package com.miniProjet.kinesitherapie.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "prestations")
public class Prestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false)
    private double tarif;

    @ManyToOne
    @JoinColumn(name = "statistiques_id")
    private Statistiques statistiques;
}
