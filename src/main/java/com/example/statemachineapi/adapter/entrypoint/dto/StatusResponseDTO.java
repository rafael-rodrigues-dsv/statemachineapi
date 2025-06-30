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
public class StatusResponseDTO {
    private UUID id;
    private String name;
    private Boolean isInitial;
}