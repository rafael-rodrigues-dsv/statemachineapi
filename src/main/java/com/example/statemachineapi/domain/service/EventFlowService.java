package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.domain.model.EventFlowModel;
import com.example.statemachineapi.domain.model.EventModel;

import java.util.List;
import java.util.UUID;

public interface EventFlowService {
    void save(EventModel event);

    List<EventFlowModel> getAll(UUID stateMachineId, UUID eventId);
}
