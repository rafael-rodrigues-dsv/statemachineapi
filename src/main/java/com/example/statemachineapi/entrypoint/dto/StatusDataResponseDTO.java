package com.example.statemachineapi.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StatusDataResponseDTO {
    private UUID id;
    private String name;
    private Boolean isInitial;
    private Boolean isActive;
    private StateMachineResponseDTO stateMachine;
}