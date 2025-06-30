package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.EventModel;
import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.service.EventService;
import com.example.statemachineapi.domain.service.StateMachineService;
import com.example.statemachineapi.domain.service.StatusService;
import com.example.statemachineapi.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventServiceImpl implements EventService {

    private final StateMachineService stateMachineService;
    private final StatusService statusService;
    private final EventRepository eventRepository;

    @Override
    public EventModel create(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineService.getById(stateMachineId);

        StatusModel initialStatus = statusService.getInitialStatus(stateMachineId);

        EventModel eventInitialStatus = EventModel.builder()
                .stateMachine(stateMachine)
                .status(initialStatus)
                .build();

        return eventRepository.save(eventInitialStatus);
    }


    @Override
    public List<EventModel> getAll(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineService.getById(stateMachineId);

        return eventRepository.findAll()
                .stream()
                .filter(filter -> filter.getStateMachine().equals(stateMachine))
                .toList();
    }

    @Override
    public EventModel getById(UUID stateMachineId, UUID id) {
        StateMachineModel stateMachine = stateMachineService.getById(stateMachineId);

        return eventRepository.findAll()
                .stream()
                .filter(filter -> filter.getStateMachine().equals(stateMachine) && filter.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public EventModel update(UUID stateMachineId, UUID id, EventModel model) {
        EventModel eventToUpdate = eventRepository.findById(id).orElseThrow();
        StatusModel status = statusService.getById(model.getStatus().getId());
        eventToUpdate.setStatus(status);
        return eventRepository.save(eventToUpdate);
    }
}