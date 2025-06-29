package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.EventModel;
import com.example.statemachineapi.entrypoint.dto.EventResponseDTO;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T15:24:07-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Autowired
    private StateMachineMapper stateMachineMapper;
    @Autowired
    private StatusMapper statusMapper;

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

        EventResponseDTO eventResponseDTO = new EventResponseDTO();

        eventResponseDTO.setId( model.getId() );
        eventResponseDTO.setStateMachine( stateMachineMapper.toDto( model.getStateMachine() ) );
        eventResponseDTO.setStatus( statusMapper.toDto( model.getStatus() ) );

        return eventResponseDTO;
    }
}
