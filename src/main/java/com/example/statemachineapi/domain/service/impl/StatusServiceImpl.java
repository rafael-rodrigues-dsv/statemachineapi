package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.mapper.StatusMapper;
import com.example.statemachineapi.adapter.repository.StateMachineRepository;
import com.example.statemachineapi.adapter.repository.StatusRepository;
import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.service.StatusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatusServiceImpl implements StatusService {

    private final StateMachineRepository stateMachineRepository;
    private final StatusRepository statusRepository;
    private final StatusMapper mapper;

    @Override
    public List<StatusResponseDTO> getAll(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineRepository.findById(stateMachineId).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + stateMachineId));

        return statusRepository.findAll()
                .stream()
                .filter(filter -> filter.getStateMachine().equals(stateMachine))
                .map(mapper::toDto)
                .toList();
    }
}