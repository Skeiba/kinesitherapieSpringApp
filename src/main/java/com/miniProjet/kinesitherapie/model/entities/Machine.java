package com.miniProjet.kinesitherapie.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "machines")
public class Machine  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @ManyToOne
    @JoinColumn(name = "salleId", nullable = true)
    private Salle salle;


}
