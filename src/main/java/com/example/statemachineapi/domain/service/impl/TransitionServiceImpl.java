package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.adapter.entrypoint.dto.TransitionResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.mapper.TransitionMapper;
import com.example.statemachineapi.adapter.repository.StateMachineRepository;
import com.example.statemachineapi.adapter.repository.TransitionRepository;
import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.service.TransitionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransitionServiceImpl implements TransitionService {

    private final StateMachineRepository stateMachineRepository;
    private final TransitionRepository transitionRepository;
    private final TransitionMapper mapper;

    @Override
    public List<TransitionResponseDTO> getAll(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineRepository.findById(stateMachineId).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + stateMachineId));

        return transitionRepository.findAll()
                .stream()
                .filter(filter -> filter.getStateMachine().equals(stateMachine))
                .map(mapper::toDto)
                .toList();
    }
}