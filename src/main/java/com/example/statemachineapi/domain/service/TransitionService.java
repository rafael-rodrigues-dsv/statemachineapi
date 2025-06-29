package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.entrypoint.dto.CreateTransitionRequestDTO;
import com.example.statemachineapi.entrypoint.dto.TransitionResponseDTO;

import java.util.UUID;

public interface TransitionService {
    TransitionResponseDTO create(CreateTransitionRequestDTO dto);

    TransitionResponseDTO getById(UUID id);

    TransitionResponseDTO disable(UUID id);
}