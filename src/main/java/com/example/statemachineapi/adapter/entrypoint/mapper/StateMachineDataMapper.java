package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.adapter.entrypoint.dto.CreateStateMachineRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StateMachineDataResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StatusDataMapper.class, TransitionMapper.class})
public interface StateMachineDataMapper {
    StateMachineModel toModel(CreateStateMachineRequestDTO dto);

    @Mapping(target = "statuses", source = "statuses")
    @Mapping(target = "transitions", source = "transitions")
    StateMachineDataResponseDTO toDto(StateMachineModel model);
}