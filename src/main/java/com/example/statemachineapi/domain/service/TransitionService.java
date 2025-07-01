package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.domain.model.TransitionModel;

import java.util.List;
import java.util.UUID;

public interface TransitionService {
    List<TransitionModel> getAll(UUID stateMachineId);

    Boolean exists(UUID stateMachineId, UUID idSourceStatus, UUID idTargetStatus);
}