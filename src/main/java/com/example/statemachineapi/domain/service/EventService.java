package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.entrypoint.dto.UpdateEventRequestDTO;

import java.util.UUID;

public interface EventService {
    EventResponseDTO create(UUID stateMachineId);

    EventResponseDTO getById(UUID eventId);

    EventResponseDTO update(UUID eventId, UpdateEventRequestDTO dto);
}