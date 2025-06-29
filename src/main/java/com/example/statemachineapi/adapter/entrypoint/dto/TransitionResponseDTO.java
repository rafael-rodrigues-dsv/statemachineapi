package com.example.statemachineapi.adapter.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TransitionResponseDTO {
    private UUID id;
    private StateMachineResponseDTO stateMachine;
    private StatusResponseDTO sourceStatus;
    private StatusResponseDTO targetStatus;
}