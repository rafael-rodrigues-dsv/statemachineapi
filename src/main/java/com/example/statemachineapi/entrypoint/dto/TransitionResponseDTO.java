package com.example.statemachineapi.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TransitionResponseDTO {
    private UUID id;
    private Boolean isActive;
    private StateMachineResponseDTO stateMachine;
    private StatusResponseDTO sourceStatus;
    private StatusResponseDTO targetStatus;
}