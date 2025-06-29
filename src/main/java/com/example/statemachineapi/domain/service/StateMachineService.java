package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.entrypoint.dto.CreateStateMachineRequestDTO;
import com.example.statemachineapi.entrypoint.dto.StateMachineDataResponseDTO;

import java.util.List;
import java.util.UUID;

public interface StateMachineService {
    StateMachineDataResponseDTO create(CreateStateMachineRequestDTO dto);

    List<StateMachineDataResponseDTO> getAll();

    StateMachineDataResponseDTO getById(UUID id);

    StateMachineDataResponseDTO disable(UUID id);
}