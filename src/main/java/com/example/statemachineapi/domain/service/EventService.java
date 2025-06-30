package com.example.statemachineapi.domain.service;

import com.example.statemachineapi.domain.model.EventModel;

import java.util.List;
import java.util.UUID;

public interface EventService {
    EventModel create(UUID stateMachineId);

    List<EventModel> getAll(UUID stateMachineId);

    EventModel getById(UUID stateMachineId, UUID id);

    EventModel update(UUID stateMachineId, UUID eventId, EventModel model);
}