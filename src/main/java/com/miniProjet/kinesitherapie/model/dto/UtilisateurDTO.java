package com.miniProjet.kinesitherapie.model.dto;


import com.miniProjet.kinesitherapie.model.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UtilisateurDTO {
    private String fullname;  // For first name and last name
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDate dob;
    private String gender;
    private Role role;
}
