package com.example.statemachineapi.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class EventResponseDTO {
    private UUID id;
    private StateMachineResponseDTO stateMachine;
    private StatusResponseDTO status;
}