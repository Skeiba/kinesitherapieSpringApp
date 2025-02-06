package com.miniProjet.kinesitherapie.auth.dto;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailRequestDTO {
    @NotBlank(message = AppConstants.INVALID_EMAIL)
    @Email(message = "Invalid email format")
    private String email;
}
