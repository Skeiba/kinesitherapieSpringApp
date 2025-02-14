package com.miniProjet.kinesitherapie.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PrestationDTO extends CreatePrestationDTO{
    private Long id;
}
