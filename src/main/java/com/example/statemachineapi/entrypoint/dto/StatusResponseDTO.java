package com.example.statemachineapi.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StatusResponseDTO {
    private UUID id;
    private String name;
    private Boolean isInitial;
    private Boolean isActive;
}