package com.example.statemachineapi.adapter.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EventFlowResponseDTO {
    private UUID id;
    private EventResponseDTO event;
    private LocalDateTime createdAt;
}
