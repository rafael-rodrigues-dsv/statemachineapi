package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.entrypoint.dto.StatusResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusResponseDTO toDto(StatusModel model);
}