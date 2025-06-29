package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.entrypoint.dto.StateMachineResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMachineMapper {
    StateMachineResponseDTO toDto(StateMachineModel model);
}