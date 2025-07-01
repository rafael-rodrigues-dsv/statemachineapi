package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.EventFlowResponseDTO;
import com.example.statemachineapi.domain.model.EventFlowModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventFlowMapper {
    EventFlowResponseDTO toDto(EventFlowModel model);
}
