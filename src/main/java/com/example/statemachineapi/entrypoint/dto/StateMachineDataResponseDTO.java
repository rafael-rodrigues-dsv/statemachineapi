package com.example.statemachineapi.entrypoint.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class StateMachineDataResponseDTO {
    private UUID id;
    private String name;
    private Boolean isActive;
    private List<StatusDataResponseDTO> statuses;
    private List<TransitionResponseDTO> transitions;
}