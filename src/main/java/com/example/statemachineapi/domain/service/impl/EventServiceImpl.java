package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.adapter.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.UpdateEventRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.mapper.EventMapper;
import com.example.statemachineapi.adapter.repository.EventRepository;
import com.example.statemachineapi.adapter.repository.StateMachineRepository;
import com.example.statemachineapi.adapter.repository.StatusRepository;
import com.example.statemachineapi.domain.model.EventModel;
import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private StateMachineRepository stateMachineRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper mapper;

    @Override
    public EventResponseDTO create(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineRepository.findById(stateMachineId).orElseThrow();
        StatusModel initialStatus = statusRepository.findByStateMachineAndIsInitialTrue(stateMachine).orElseThrow();
        EventModel eventInitialStatus = EventModel.builder()
                .stateMachine(stateMachine)
                .status(initialStatus)
                .build();
        return mapper.toDto(eventRepository.save(eventInitialStatus));
    }

    @Override
    public EventResponseDTO getById(UUID stateMachineId, UUID eventId) {
        return mapper.toDto(eventRepository.findById(eventId).orElseThrow());
    }

    @Override
    public EventResponseDTO update(UUID stateMachineId, UUID eventId, UpdateEventRequestDTO dto) {
        EventModel eventToUpdate = eventRepository.findById(eventId).orElseThrow();
        StatusModel status = statusRepository.findById(dto.getStatusId()).orElseThrow();
        eventToUpdate.setStatus(status);
        return mapper.toDto(eventRepository.save(eventToUpdate));
    }
}