package com.example.statemachineapi.repository;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.TransitionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransitionRepository extends JpaRepository<TransitionModel, UUID> {
    List<TransitionModel> findByStateMachine(StateMachineModel sm);
}