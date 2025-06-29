package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.EventModel;
import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.service.EventService;
import com.example.statemachineapi.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.entrypoint.dto.UpdateEventRequestDTO;
import com.example.statemachineapi.entrypoint.mapper.EventMapper;
import com.example.statemachineapi.repository.EventRepository;
import com.example.statemachineapi.repository.StateMachineRepository;
import com.example.statemachineapi.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private StateMachineRepository smRepo;
    @Autowired
    private StatusRepository statusRepo;
    @Autowired
    private EventRepository repo;
    @Autowired
    private EventMapper mapper;

    @Override
    public EventResponseDTO create(UUID stateMachineId) {
        StateMachineModel sm = smRepo.findById(stateMachineId).orElseThrow();
        StatusModel init = statusRepo.findByStateMachineAndIsInitialTrue(sm).orElseThrow();
        EventModel ev = EventModel.builder().stateMachine(sm).status(init).build();
        return mapper.toDto(repo.save(ev));
    }

    @Override
    public EventResponseDTO getById(UUID eventId) {
        return mapper.toDto(repo.findById(eventId).orElseThrow());
    }

    @Override
    public EventResponseDTO update(UUID eventId, UpdateEventRequestDTO dto) {
        EventModel ev = repo.findById(eventId).orElseThrow();
        StatusModel st = statusRepo.findById(dto.getStatusId()).orElseThrow();
        ev.setStatus(st);
        return mapper.toDto(repo.save(ev));
    }
}