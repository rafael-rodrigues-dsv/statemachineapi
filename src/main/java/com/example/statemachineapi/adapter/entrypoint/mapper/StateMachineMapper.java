package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.StateMachineResponseDTO;
import com.example.statemachineapi.domain.model.StateMachineModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StateMachineMapper {
    StateMachineResponseDTO toDto(StateMachineModel model);
}
