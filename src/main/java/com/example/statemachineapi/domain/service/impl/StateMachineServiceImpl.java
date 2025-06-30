package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.service.StateMachineService;
import com.example.statemachineapi.repository.StateMachineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateMachineServiceImpl implements StateMachineService {

    private final StateMachineRepository stateMachineRepository;

    @Override
    public List<StateMachineModel> getAll() {
        return stateMachineRepository.findAll()
                .stream()
                .toList();
    }

    @Override
    public StateMachineModel getById(UUID id) {
        return stateMachineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + id));
    }

    @Override
    public StateMachineModel disable(UUID id) {
        StateMachineModel status = stateMachineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + id));
        status.setIsActive(Boolean.FALSE);
        return stateMachineRepository.save(status);
    }
}