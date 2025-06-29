package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.entrypoint.dto.StateMachineResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T15:24:30-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class StateMachineMapperImpl implements StateMachineMapper {

    @Override
    public StateMachineResponseDTO toDto(StateMachineModel model) {
        if ( model == null ) {
            return null;
        }

        StateMachineResponseDTO stateMachineResponseDTO = new StateMachineResponseDTO();

        stateMachineResponseDTO.setId( model.getId() );
        stateMachineResponseDTO.setName( model.getName() );
        stateMachineResponseDTO.setIsActive( model.getIsActive() );

        return stateMachineResponseDTO;
    }
}
