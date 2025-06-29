package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.entrypoint.dto.CreateStatusRequestDTO;
import com.example.statemachineapi.entrypoint.dto.StatusDataResponseDTO;

import java.util.UUID;

public interface StatusService {
    StatusDataResponseDTO create(CreateStatusRequestDTO dto);

    StatusDataResponseDTO getById(UUID id);

    StatusDataResponseDTO disable(UUID id);
}