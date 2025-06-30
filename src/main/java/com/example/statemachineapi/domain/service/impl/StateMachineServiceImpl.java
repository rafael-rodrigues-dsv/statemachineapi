package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.adapter.entrypoint.dto.StateMachineResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.mapper.StateMachineMapper;
import com.example.statemachineapi.adapter.repository.StateMachineRepository;
import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.service.StateMachineService;
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
    private final StateMachineMapper mapper;

    @Override
    public List<StateMachineResponseDTO> getAll() {
        return stateMachineRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public StateMachineResponseDTO getById(UUID id) {
        return mapper.toDto(stateMachineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + id)));
    }

    @Override
    public StateMachineResponseDTO disable(UUID id) {
        StateMachineModel status = stateMachineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("State Machine not found with id " + id));
        status.setIsActive(Boolean.FALSE);
        return mapper.toDto(stateMachineRepository.save(status));
    }
}