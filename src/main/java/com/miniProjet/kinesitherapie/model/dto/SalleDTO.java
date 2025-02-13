package com.miniProjet.kinesitherapie.model.dto;

import com.miniProjet.kinesitherapie.model.enums.RessourceStatus;
import lombok.Data;

@Data
public class SalleDTO {
    private Long id;
    private String name;
    private String location;
    private RessourceStatus status;
    private int capacity;
    private int queue;
}

