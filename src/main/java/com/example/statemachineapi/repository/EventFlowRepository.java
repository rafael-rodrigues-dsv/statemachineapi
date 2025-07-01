package com.example.statemachineapi.repository;

import com.example.statemachineapi.domain.model.EventFlowModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventFlowRepository extends JpaRepository<EventFlowModel, UUID> {
}
