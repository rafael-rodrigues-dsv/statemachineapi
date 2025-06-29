package com.example.statemachineapi.repository;

import com.example.statemachineapi.domain.model.StateMachineModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateMachineRepository extends JpaRepository<StateMachineModel, UUID> {
}