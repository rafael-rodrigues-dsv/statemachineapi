package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.TransitionResponseDTO;
import com.example.statemachineapi.domain.model.TransitionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransitionMapper {
    TransitionResponseDTO toDto(TransitionModel model);
}