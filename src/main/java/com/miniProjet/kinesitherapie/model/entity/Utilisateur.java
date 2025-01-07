package com.miniProjet.kinesitherapie.model.entity;

public class Utilisateur {
    private Long id;
    private String nom;
    private String prenom;
    private String login;
    private String motsDePasse;
    private Role role; //for one role or Set<Role> roles if we want multiple roles
}
