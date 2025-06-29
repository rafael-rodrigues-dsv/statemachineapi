package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.service.StateMachineService;
import com.example.statemachineapi.entrypoint.dto.CreateStateMachineRequestDTO;
import com.example.statemachineapi.entrypoint.dto.StateMachineDataResponseDTO;
import com.example.statemachineapi.entrypoint.mapper.StateMachineDataMapper;
import com.example.statemachineapi.repository.StateMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StateMachineServiceImpl implements StateMachineService {
    @Autowired
    private StateMachineRepository repo;
    @Autowired
    private StateMachineDataMapper mapper;

    @Override
    public StateMachineDataResponseDTO create(CreateStateMachineRequestDTO dto) {
        StateMachineModel model = mapper.toModel(dto);
        model.setIsActive(true);
        StateMachineModel saved = repo.save(model);
        return mapper.toDto(saved);
    }

    @Override
    public List<StateMachineDataResponseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public StateMachineDataResponseDTO getById(UUID id) {
        StateMachineModel m = repo.findById(id).orElseThrow();
        return mapper.toDto(m);
    }

    @Override
    public StateMachineDataResponseDTO disable(UUID id) {
        StateMachineModel m = repo.findById(id).orElseThrow();
        m.setIsActive(false);
        return mapper.toDto(repo.save(m));
    }
}