package com.miniProjet.kinesitherapie.model.entities;

import com.miniProjet.kinesitherapie.model.enums.PrestationType;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "prestations")
//it's a service like it has a type name and tarif aka money
public class Prestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppConstants.INVALID_NOM)
    @Column(nullable = false, length = 50)
    private String nom;

    @NotNull(message = "Type can't be empty")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private PrestationType type;

    @Column(nullable = false)
    @Positive(message = "Tarif must be a positive number")
    private double tarif;

    @ManyToOne
    @JoinColumn(name = "statistiques_id")
    private Statistiques statistiques;
}
