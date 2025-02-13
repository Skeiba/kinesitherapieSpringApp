package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.utils.AppConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationInfoDTO {
    @Positive
    private Long id;

    @NotBlank(message = AppConstants.INVALID_MESSAGE)
    private String message;

    @NotBlank(message = AppConstants.INVALID_DATE)
    private LocalDate dateEnvoi;
}
