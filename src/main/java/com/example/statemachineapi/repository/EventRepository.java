package com.example.statemachineapi.repository;

import com.example.statemachineapi.domain.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<EventModel, UUID> {
}