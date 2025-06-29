package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.TransitionModel;
import com.example.statemachineapi.entrypoint.dto.CreateTransitionRequestDTO;
import com.example.statemachineapi.entrypoint.dto.TransitionResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StateMachineMapper.class, StatusMapper.class})
public interface TransitionMapper {
    TransitionModel toModel(CreateTransitionRequestDTO dto);

    TransitionResponseDTO toDto(TransitionModel model);
}