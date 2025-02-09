package com.miniProjet.kinesitherapie.model.entities;

import com.miniProjet.kinesitherapie.model.enums.RessourceStatus;
import com.miniProjet.kinesitherapie.model.enums.Role;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "salles")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppConstants.INVALID_NOM)
    @Column(nullable = false, length = 50)
    private String nom;


    @Column(nullable = false)
     private String Location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RessourceStatus status;

    @Column(nullable = false)
    private int nombreLits;
}
