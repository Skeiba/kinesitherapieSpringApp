package com.miniProjet.kinesitherapie.model.entities;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "prestations")
public class Prestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppConstants.INVALID_NOM)
    @Column(nullable = false, length = 50)
    private String nom;

    @NotBlank(message = "Type can't be empty")
    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false)
    @Positive(message = "Tarif must be a positive number")
    private double tarif;

    @ManyToOne
    @JoinColumn(name = "statistiques_id")
    private Statistiques statistiques;
}
