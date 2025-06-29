package com.example.statemachineapi.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTransitionRequestDTO {
    private UUID stateMachineId;
    private UUID sourceStatusId;
    private UUID targetStatusId;
}