package com.example.statemachineapi.adapter.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StateMachineResponseDTO {
    private UUID id;
    private String name;
    private Boolean isActive;
}