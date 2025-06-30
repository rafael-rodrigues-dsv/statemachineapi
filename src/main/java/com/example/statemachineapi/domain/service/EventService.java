package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.adapter.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.UpdateEventRequestDTO;

import java.util.List;
import java.util.UUID;

public interface EventService {
    EventResponseDTO create(UUID stateMachineId);
    List<EventResponseDTO> getAll(UUID stateMachineId);
    EventResponseDTO getById(UUID stateMachineId, UUID id);
    EventResponseDTO update(UUID stateMachineId, UUID eventId, UpdateEventRequestDTO dto);
}