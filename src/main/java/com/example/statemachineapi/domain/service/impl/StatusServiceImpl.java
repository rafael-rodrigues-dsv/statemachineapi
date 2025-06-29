package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.service.StatusService;
import com.example.statemachineapi.adapter.entrypoint.dto.CreateStatusRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StatusDataResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.mapper.StatusDataMapper;
import com.example.statemachineapi.adapter.repository.StateMachineRepository;
import com.example.statemachineapi.adapter.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StateMachineRepository stateMachineRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private StatusDataMapper mapper;

    @Override
    public StatusDataResponseDTO create(UUID stateMachineId, CreateStatusRequestDTO dto) {
        StateMachineModel sm = stateMachineRepository.findById(stateMachineId).orElseThrow();
        StatusModel model = mapper.toModel(dto);
        model.setStateMachine(sm);
        StatusModel saved = statusRepository.save(model);
        return mapper.toDto(saved);
    }

    @Override
    public StatusDataResponseDTO getById(UUID stateMachineId, UUID id) {
        return mapper.toDto(statusRepository.findById(id).orElseThrow());
    }
}