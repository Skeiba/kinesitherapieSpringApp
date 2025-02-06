package com.miniProjet.kinesitherapie.model.entities;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Data
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppConstants.INVALID_NOM)
    @Column(nullable = false, length = 50)
    private String nom;

    @NotBlank(message = AppConstants.INVALID_PRENOM)
    @Column(nullable = false, length = 50)
    private String prenom;

    @NotBlank(message = AppConstants.INVALID_ADRESSE)
    @Column(nullable = false, length = 50)
    private String adresse;
    //add male and female and dateofbirth
    @NotNull(message = "Email cannot be null")
    @Email(message = AppConstants.INVALID_EMAIL)
    @Column(nullable = false,unique = true, length = 50)
    private String email;

    @NotBlank(message = "Telephone cannot be blank")
    @Pattern(regexp = "^(06|07|05)[0-9]{8}$", message = AppConstants.INVALID_PHONE_NUMBER)
    @Column(nullable = false, unique = true, length = 15)
    private String telephone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FicheMedical> fichesMedicales;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RendezVous> rendezVous;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Paiement> paiements;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Notification> notifications;
}
