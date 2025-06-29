package com.example.statemachineapi.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateStatusRequestDTO {
    private UUID stateMachineId;
    private String name;
    private Boolean isInitial;
}