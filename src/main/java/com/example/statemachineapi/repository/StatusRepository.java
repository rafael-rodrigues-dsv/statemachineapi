package com.example.statemachineapi.adapter.repository;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<StatusModel, UUID> {
    Optional<StatusModel> findByStateMachineAndIsInitialTrue(StateMachineModel sm);
}