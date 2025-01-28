package com.miniProjet.kinesitherapie.model.entities;

import com.miniProjet.kinesitherapie.model.enums.Role;
import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
    private String motsDePasse;

    private boolean loggedIn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;
}
