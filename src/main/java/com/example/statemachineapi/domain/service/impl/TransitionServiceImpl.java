package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.adapter.entrypoint.dto.CreateTransitionRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.TransitionResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.mapper.TransitionMapper;
import com.example.statemachineapi.adapter.repository.StateMachineRepository;
import com.example.statemachineapi.adapter.repository.StatusRepository;
import com.example.statemachineapi.adapter.repository.TransitionRepository;
import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.model.TransitionModel;
import com.example.statemachineapi.domain.service.TransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransitionServiceImpl implements TransitionService {
    @Autowired
    private StateMachineRepository stateMachineRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private TransitionRepository transitionRepository;

    @Autowired
    private TransitionMapper mapper;

    @Override
    public TransitionResponseDTO create(UUID stateMachineId, CreateTransitionRequestDTO dto) {
        StateMachineModel stateMachine = stateMachineRepository.findById(stateMachineId).orElseThrow();
        StatusModel statusSource = statusRepository.findById(dto.getSourceStatusId()).orElseThrow();
        StatusModel statusTarget = statusRepository.findById(dto.getTargetStatusId()).orElseThrow();
        TransitionModel transition = mapper.toModel(dto);
        transition.setStateMachine(stateMachine);
        transition.setSourceStatus(statusSource);
        transition.setTargetStatus(statusTarget);
        return mapper.toDto(transitionRepository.save(transition));
    }

    @Override
    public TransitionResponseDTO getById(UUID stateMachineId, UUID id) {
        return mapper.toDto(transitionRepository.findById(id).orElseThrow());
    }
}