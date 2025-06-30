package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.service.StateMachineService;
import com.example.statemachineapi.domain.service.StatusService;
import com.example.statemachineapi.repository.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatusServiceImpl implements StatusService {

    private final StateMachineService stateMachineService;
    private final StatusRepository statusRepository;

    @Override
    public StatusModel getInitialStatus(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineService.getById(stateMachineId);
        return statusRepository.findByStateMachineAndIsInitialTrue(stateMachine).orElseThrow(() -> new EntityNotFoundException("Initial State Machine not found with id " + stateMachineId));
    }

    @Override
    public StatusModel getById(UUID id) {
        return statusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Status not found with id " + id));
    }

    @Override
    public List<StatusModel> getAll(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineService.getById(stateMachineId);

        return statusRepository.findAll()
                .stream()
                .filter(filter -> filter.getStateMachine().equals(stateMachine))
                .toList();
    }
}