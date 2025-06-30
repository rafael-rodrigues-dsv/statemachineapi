package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;
import com.example.statemachineapi.domain.model.StatusModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusResponseDTO toDto(StatusModel model);
}
