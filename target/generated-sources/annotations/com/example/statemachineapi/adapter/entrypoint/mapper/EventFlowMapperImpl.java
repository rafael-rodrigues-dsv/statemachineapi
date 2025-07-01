package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.EventFlowResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;
import com.example.statemachineapi.domain.model.EventFlowModel;
import com.example.statemachineapi.domain.model.EventModel;
import com.example.statemachineapi.domain.model.StatusModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-30T21:42:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class EventFlowMapperImpl implements EventFlowMapper {

    @Override
    public EventFlowResponseDTO toDto(EventFlowModel model) {
        if ( model == null ) {
            return null;
        }

        EventFlowResponseDTO.EventFlowResponseDTOBuilder eventFlowResponseDTO = EventFlowResponseDTO.builder();

        eventFlowResponseDTO.id( model.getId() );
        eventFlowResponseDTO.event( eventModelToEventResponseDTO( model.getEvent() ) );
        eventFlowResponseDTO.createdAt( model.getCreatedAt() );

        return eventFlowResponseDTO.build();
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

    protected EventResponseDTO eventModelToEventResponseDTO(EventModel eventModel) {
        if ( eventModel == null ) {
            return null;
        }

        EventResponseDTO.EventResponseDTOBuilder eventResponseDTO = EventResponseDTO.builder();

        eventResponseDTO.id( eventModel.getId() );
        eventResponseDTO.status( statusModelToStatusResponseDTO( eventModel.getStatus() ) );

        return eventResponseDTO.build();
    }
}
