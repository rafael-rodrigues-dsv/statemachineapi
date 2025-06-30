package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.adapter.entrypoint.dto.StateMachineResponseDTO;

import java.util.List;
import java.util.UUID;

public interface StateMachineService {
    List<StateMachineResponseDTO> getAll();
    StateMachineResponseDTO getById(UUID id);
    StateMachineResponseDTO disable(UUID id);
}