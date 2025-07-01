package com.example.statemachineapi.repository;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.TransitionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransitionRepository extends JpaRepository<TransitionModel, UUID> {
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TransitionModel t " +
            "WHERE t.stateMachine.id = :stateMachineId " +
            "AND t.sourceStatus.id = :sourceStatusId " +
            "AND t.targetStatus.id = :targetStatusId")
    boolean existsTransition(
            @Param("stateMachineId") UUID stateMachineId,
            @Param("sourceStatusId") UUID sourceStatusId,
            @Param("targetStatusId") UUID targetStatusId
    );
}