package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.domain.model.StateMachineModel;

import java.util.List;
import java.util.UUID;

public interface StateMachineService {
    List<StateMachineModel> getAll();

    StateMachineModel getById(UUID id);

    StateMachineModel disable(UUID id);
}