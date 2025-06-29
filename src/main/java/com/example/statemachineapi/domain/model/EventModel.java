package com.example.statemachineapi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "state_machine_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventModel {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "state_machine_id")
    private StateMachineModel stateMachine;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel status;
}