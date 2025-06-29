package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.adapter.entrypoint.dto.CreateTransitionRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.TransitionResponseDTO;

import java.util.UUID;

public interface TransitionService {
    TransitionResponseDTO create(UUID stateMachineId, CreateTransitionRequestDTO dto);
    TransitionResponseDTO getById(UUID stateMachineId, UUID id);
}