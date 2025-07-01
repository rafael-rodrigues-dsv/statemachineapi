package com.example.statemachineapi.domain.service.impl;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.TransitionModel;
import com.example.statemachineapi.domain.service.StateMachineService;
import com.example.statemachineapi.domain.service.StatusService;
import com.example.statemachineapi.domain.service.TransitionService;
import com.example.statemachineapi.repository.TransitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransitionServiceImpl implements TransitionService {

    private final StateMachineService stateMachineService;
    private final TransitionRepository transitionRepository;

    @Override
    public List<TransitionModel> getAll(UUID stateMachineId) {
        StateMachineModel stateMachine = stateMachineService.getById(stateMachineId);

        return transitionRepository.findAll()
                .stream()
                .filter(filter -> filter.getStateMachine().equals(stateMachine))
                .toList();
    }

    @Override
    public Boolean exists(UUID stateMachineId, UUID idSourceStatus, UUID idTargetStatus) {
        return transitionRepository.existsTransition(stateMachineId, idSourceStatus, idTargetStatus);
    }
}