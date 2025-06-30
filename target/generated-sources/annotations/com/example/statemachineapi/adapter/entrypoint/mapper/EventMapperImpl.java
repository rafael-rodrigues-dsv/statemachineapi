package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;
import com.example.statemachineapi.domain.model.EventModel;
import com.example.statemachineapi.domain.model.StatusModel;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-30T13:13:41-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventModel toModel(UUID stateMachineId, UUID statusId) {
        if ( stateMachineId == null && statusId == null ) {
            return null;
        }

        EventModel.EventModelBuilder eventModel = EventModel.builder();

        return eventModel.build();
    }

    @Override
    public EventResponseDTO toDto(EventModel model) {
        if ( model == null ) {
            return null;
        }

        EventResponseDTO.EventResponseDTOBuilder eventResponseDTO = EventResponseDTO.builder();

        eventResponseDTO.id( model.getId() );
        eventResponseDTO.status( statusModelToStatusResponseDTO( model.getStatus() ) );

        return eventResponseDTO.build();
    }

    protected StatusResponseDTO statusModelToStatusResponseDTO(StatusModel statusModel) {
        if ( statusModel == null ) {
            return null;
        }

        StatusResponseDTO.StatusResponseDTOBuilder statusResponseDTO = StatusResponseDTO.builder();

        statusResponseDTO.id( statusModel.getId() );
        statusResponseDTO.name( statusModel.getName() );
        statusResponseDTO.isInitial( statusModel.getIsInitial() );

        return statusResponseDTO.build();
    }
}
