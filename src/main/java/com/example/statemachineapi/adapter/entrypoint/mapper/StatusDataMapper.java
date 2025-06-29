package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.adapter.entrypoint.dto.CreateStatusRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StatusDataResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = StateMachineMapper.class)
public interface StatusDataMapper {
    StatusModel toModel(CreateStatusRequestDTO dto);

    @Mapping(target = "stateMachine", source = "stateMachine")
    StatusDataResponseDTO toDto(StatusModel model);
}