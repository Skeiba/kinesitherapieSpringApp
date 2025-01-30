package com.miniProjet.kinesitherapie.model.entities;

import com.miniProjet.kinesitherapie.model.enums.RessourceStatus;
import com.miniProjet.kinesitherapie.model.enums.RessourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salles")
@Data
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private int nombreLits;

    @Column(nullable = false, length = 50)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RessourceStatus status = RessourceStatus.AVAILABLE;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Machine> machineList;




}
