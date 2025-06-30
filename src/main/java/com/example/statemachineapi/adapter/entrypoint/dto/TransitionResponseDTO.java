package com.example.statemachineapi.adapter.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransitionResponseDTO {
    private UUID id;
    private StatusResponseDTO sourceStatus;
    private StatusResponseDTO targetStatus;
}