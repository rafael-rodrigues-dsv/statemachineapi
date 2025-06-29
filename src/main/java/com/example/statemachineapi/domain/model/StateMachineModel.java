package com.example.statemachineapi.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "state_machine")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateMachineModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private Boolean isActive;

    @OneToMany(mappedBy = "stateMachine", cascade = CascadeType.ALL)
    private List<StatusModel> statuses = new ArrayList<>();

    @OneToMany(mappedBy = "stateMachine", cascade = CascadeType.ALL)
    private List<TransitionModel> transitions = new ArrayList<>();
}