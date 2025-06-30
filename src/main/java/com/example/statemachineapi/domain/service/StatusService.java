package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.domain.model.StatusModel;

import java.util.List;
import java.util.UUID;

public interface StatusService {
    StatusModel getInitialStatus(UUID stateMachineId);
    StatusModel getById(UUID id);
    List<StatusModel> getAll(UUID stateMachineId);
}