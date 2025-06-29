package com.example.statemachineapi.adapter.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTransitionRequestDTO {
    private UUID sourceStatusId;
    private UUID targetStatusId;
}