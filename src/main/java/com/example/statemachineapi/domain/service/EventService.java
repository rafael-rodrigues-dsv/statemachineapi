package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.adapter.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.UpdateEventRequestDTO;

import java.util.UUID;

public interface EventService {
    EventResponseDTO create(UUID stateMachineId);
    EventResponseDTO getById(UUID stateMachineId, UUID eventId);
    EventResponseDTO update(UUID stateMachineId, UUID eventId, UpdateEventRequestDTO dto);
}