package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.EventModel;
import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.service.EventFlowService;
import com.example.statemachineapi.domain.service.EventService;
import com.example.statemachineapi.domain.service.StateMachineService;
import com.example.statemachineapi.domain.service.StatusService;
import com.example.statemachineapi.domain.service.TransitionService;
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
    private final EventFlowService eventFlowService;
    private final EventRepository eventRepository;
//    private final TransitionService transitionService;

    @Override
    public EventModel create(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineService.getById(stateMachineId);

        StatusModel initialStatus = statusService.getInitialStatus(stateMachineId);

        EventModel eventInitialStatus = EventModel.builder()
                .stateMachine(stateMachine)
                .status(initialStatus)
                .build();
        EventModel eventModel = eventRepository.save(eventInitialStatus);
        eventFlowService.save(eventModel);
        return eventModel;
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
        EventModel eventToUpdate = getById(stateMachineId, id);
        StatusModel newStatus = statusService.getById(model.getStatus().getId());
//        if (!transitionService.exists(stateMachineId, eventToUpdate.getId(), newStatus.getId())) {
//            new EntityNotFoundException("Invalid or not allowed transition from status " + eventToUpdate.getStatus().getName() + " to status " + newStatus.getName());
//        }
        eventToUpdate.setStatus(newStatus);
        EventModel eventModel = eventRepository.save(eventToUpdate);
        eventFlowService.save(eventModel);
        return eventModel;
    }
}