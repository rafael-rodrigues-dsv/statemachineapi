package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.domain.model.EventModel;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventModel toModel(UUID stateMachineId, UUID statusId);

    EventResponseDTO toDto(EventModel model);
}