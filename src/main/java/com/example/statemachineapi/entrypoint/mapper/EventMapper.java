package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.EventModel;
import com.example.statemachineapi.entrypoint.dto.EventResponseDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {StateMachineMapper.class, StatusMapper.class})
public interface EventMapper {
    EventModel toModel(UUID stateMachineId, UUID statusId);

    EventResponseDTO toDto(EventModel model);
}