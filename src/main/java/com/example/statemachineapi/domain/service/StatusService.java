package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;

import java.util.List;
import java.util.UUID;

public interface StatusService {
    List<StatusResponseDTO> getAll(UUID stateMachineId);
}