package com.miniProjet.kinesitherapie.auth.dto;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VerificationDTO {
    @NotBlank(message = AppConstants.INVALID_EMAIL)
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Verification code is required")
    @Size(min = 6, max = 6, message = "Verification code must be 6 digits")
    private String verificationCode;
}
