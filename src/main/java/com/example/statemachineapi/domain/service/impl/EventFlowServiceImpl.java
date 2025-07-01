package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.EventFlowModel;
import com.example.statemachineapi.domain.model.EventModel;
import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.repository.EventFlowRepository;
import com.example.statemachineapi.domain.service.EventFlowService;
import com.example.statemachineapi.domain.service.StateMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventFlowServiceImpl implements EventFlowService {

    private final StateMachineService stateMachineService;
    private final EventFlowRepository eventFlowRepository;

    @Override
    public void save(EventModel event) {
        EventFlowModel flow = EventFlowModel.builder()
                .event(event)
                .createdAt(LocalDateTime.now())
                .build();

        eventFlowRepository.save(flow);
    }

    @Override
    public List<EventFlowModel> getAll(UUID stateMachineId, UUID eventId) {
        StateMachineModel stateMachine = stateMachineService.getById(stateMachineId);

        return eventFlowRepository.findAll()
                .stream()
                .filter(filter -> filter.getEvent().getStateMachine().equals(stateMachine)
                        && filter.getEvent().getId().equals(eventId))
                .toList();
    }
}

