package com.example.statemachineapi.adapter.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StatusDataResponseDTO {
    private UUID id;
    private String name;
    private Boolean isInitial;
    private StateMachineResponseDTO stateMachine;
}