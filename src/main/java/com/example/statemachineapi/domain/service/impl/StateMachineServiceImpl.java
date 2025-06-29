package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.service.StateMachineService;
import com.example.statemachineapi.adapter.entrypoint.dto.CreateStateMachineRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StateMachineDataResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.mapper.StateMachineDataMapper;
import com.example.statemachineapi.adapter.repository.StateMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StateMachineServiceImpl implements StateMachineService {
    @Autowired
    private StateMachineRepository stateMachineRepository;

    @Autowired
    private StateMachineDataMapper mapper;

    @Override
    public StateMachineDataResponseDTO create(CreateStateMachineRequestDTO dto) {
        StateMachineModel stateMachine = mapper.toModel(dto);
        stateMachine.setIsActive(true);
        StateMachineModel saved = stateMachineRepository.save(stateMachine);
        return mapper.toDto(saved);
    }

    @Override
    public List<StateMachineDataResponseDTO> getAll() {
        return stateMachineRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public StateMachineDataResponseDTO getById(UUID id) {
        return mapper.toDto(stateMachineRepository.findById(id).orElseThrow());
    }

    @Override
    public StateMachineDataResponseDTO disable(UUID id) {
        StateMachineModel status = stateMachineRepository.findById(id).orElseThrow();
        status.setIsActive(Boolean.FALSE);
        return mapper.toDto(stateMachineRepository.save(status));
    }
}