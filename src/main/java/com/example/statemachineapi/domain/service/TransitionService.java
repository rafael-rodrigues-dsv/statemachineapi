package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.adapter.entrypoint.dto.TransitionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TransitionService {
    List<TransitionResponseDTO> getAll(UUID stateMachineId);
}