package com.miniProjet.kinesitherapie.model.entities;

import com.miniProjet.kinesitherapie.model.enums.Role;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppConstants.INVALID_NOM)
    @Column(nullable = false, length = 50)
    private String nom;

    @NotBlank(message = AppConstants.INVALID_PRENOM)
    @Column(nullable = false, length = 50)
    private String prenom;

    @NotNull(message = "Email cannot be null")
    @Email(message = AppConstants.INVALID_EMAIL)
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    @Column(nullable = false)
    private String motDePasse;

    @Column(name = "logged_in")
    private boolean loggedIn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    @Column(nullable = true, length = 15)
    private String phoneNumber;

    @Column(nullable = true, length = 255)
    private String address;

    @Column(nullable = true)
    private LocalDate dob;

    @Column(nullable = true, length = 10)
    private String gender;


}
