package com.miniProjet.kinesitherapie.auth.model;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppConstants.INVALID_EMAIL)
    private String email;

    @NotBlank(message = "Verification code must not be blank")
    private String code;

    @NotNull(message = AppConstants.INVALID_DATE)
    private LocalDateTime expiryDate;

    private boolean used;

}
