package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.adapter.entrypoint.dto.CreateStatusRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StatusDataResponseDTO;

import java.util.UUID;

public interface StatusService {
    StatusDataResponseDTO create(UUID stateMachineId, CreateStatusRequestDTO dto);
    StatusDataResponseDTO getById(UUID stateMachineId, UUID id);
}