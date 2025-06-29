package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.service.StatusService;
import com.example.statemachineapi.entrypoint.dto.CreateStatusRequestDTO;
import com.example.statemachineapi.entrypoint.dto.StatusDataResponseDTO;
import com.example.statemachineapi.entrypoint.mapper.StatusDataMapper;
import com.example.statemachineapi.repository.StateMachineRepository;
import com.example.statemachineapi.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StateMachineRepository smRepo;
    @Autowired
    private StatusRepository repo;
    @Autowired
    private StatusDataMapper mapper;

    @Override
    public StatusDataResponseDTO create(CreateStatusRequestDTO dto) {
        StateMachineModel sm = smRepo.findById(dto.getStateMachineId()).orElseThrow();
        StatusModel model = mapper.toModel(dto);
        model.setStateMachine(sm);
        model.setIsActive(true);
        StatusModel saved = repo.save(model);
        return mapper.toDto(saved);
    }

    @Override
    public StatusDataResponseDTO getById(UUID id) {
        return mapper.toDto(repo.findById(id).orElseThrow());
    }

    @Override
    public StatusDataResponseDTO disable(UUID id) {
        StatusModel m = repo.findById(id).orElseThrow();
        m.setIsActive(false);
        return mapper.toDto(repo.save(m));
    }
}