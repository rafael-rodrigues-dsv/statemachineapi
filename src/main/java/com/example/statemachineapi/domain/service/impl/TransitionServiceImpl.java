package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.model.TransitionModel;
import com.example.statemachineapi.domain.service.TransitionService;
import com.example.statemachineapi.entrypoint.dto.CreateTransitionRequestDTO;
import com.example.statemachineapi.entrypoint.dto.TransitionResponseDTO;
import com.example.statemachineapi.entrypoint.mapper.TransitionMapper;
import com.example.statemachineapi.repository.StateMachineRepository;
import com.example.statemachineapi.repository.StatusRepository;
import com.example.statemachineapi.repository.TransitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransitionServiceImpl implements TransitionService {
    @Autowired
    private StateMachineRepository smRepo;
    @Autowired
    private StatusRepository statusRepo;
    @Autowired
    private TransitionRepository repo;
    @Autowired
    private TransitionMapper mapper;

    @Override
    public TransitionResponseDTO create(CreateTransitionRequestDTO dto) {
        StateMachineModel sm = smRepo.findById(dto.getStateMachineId()).orElseThrow();
        StatusModel src = statusRepo.findById(dto.getSourceStatusId()).orElseThrow();
        StatusModel tgt = statusRepo.findById(dto.getTargetStatusId()).orElseThrow();
        TransitionModel model = mapper.toModel(dto);
        model.setStateMachine(sm);
        model.setSourceStatus(src);
        model.setTargetStatus(tgt);
        model.setIsActive(true);
        return mapper.toDto(repo.save(model));
    }

    @Override
    public TransitionResponseDTO getById(UUID id) {
        return mapper.toDto(repo.findById(id).orElseThrow());
    }

    @Override
    public TransitionResponseDTO disable(UUID id) {
        TransitionModel m = repo.findById(id).orElseThrow();
        m.setIsActive(false);
        return mapper.toDto(repo.save(m));
    }
}