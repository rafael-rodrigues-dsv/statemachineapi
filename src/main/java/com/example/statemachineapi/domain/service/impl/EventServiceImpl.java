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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventServiceImpl implements EventService {

    private final StateMachineRepository stateMachineRepository;
    private final StatusRepository statusRepository;
    private final EventRepository eventRepository;
    private final EventMapper mapper;

    @Override
    public EventResponseDTO create(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineRepository.findById(stateMachineId).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + stateMachineId));
        StatusModel initialStatus = statusRepository.findByStateMachineAndIsInitialTrue(stateMachine).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + stateMachineId));
        EventModel eventInitialStatus = EventModel.builder()
                .stateMachine(stateMachine)
                .status(initialStatus)
                .build();
        return mapper.toDto(eventRepository.save(eventInitialStatus));
    }


    @Override
    public List<EventResponseDTO> getAll(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineRepository.findById(stateMachineId).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + stateMachineId));

        return eventRepository.findAll()
                .stream()
                .filter(filter -> filter.getStateMachine().equals(stateMachine))
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public EventResponseDTO getById(UUID stateMachineId, UUID id) {
        StateMachineModel stateMachine = stateMachineRepository.findById(stateMachineId).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + stateMachineId));

        return eventRepository.findAll()
                .stream()
                .filter(filter -> filter.getStateMachine().equals(stateMachine) && filter.getId().equals(id))
                .map(mapper::toDto)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public EventResponseDTO update(UUID stateMachineId, UUID id, UpdateEventRequestDTO dto) {
        EventModel eventToUpdate = eventRepository.findById(id).orElseThrow();
        StatusModel status = statusRepository.findById(dto.getStatusId()).orElseThrow();
        eventToUpdate.setStatus(status);
        return mapper.toDto(eventRepository.save(eventToUpdate));
    }
}