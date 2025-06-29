package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.entrypoint.dto.CreateStatusRequestDTO;
import com.example.statemachineapi.entrypoint.dto.StatusDataResponseDTO;
import com.example.statemachineapi.entrypoint.dto.StatusResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T15:24:29-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class StatusDataMapperImpl implements StatusDataMapper {

    @Autowired
    private StateMachineMapper stateMachineMapper;

    @Override
    public StatusModel toModel(CreateStatusRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        StatusModel.StatusModelBuilder statusModel = StatusModel.builder();

        statusModel.name( dto.getName() );
        statusModel.isInitial( dto.getIsInitial() );

        return statusModel.build();
    }

    @Override
    public StatusDataResponseDTO toDto(StatusModel model) {
        if ( model == null ) {
            return null;
        }

        StatusDataResponseDTO statusDataResponseDTO = new StatusDataResponseDTO();

        statusDataResponseDTO.setStateMachine( stateMachineMapper.toDto( model.getStateMachine() ) );
        statusDataResponseDTO.setId( model.getId() );
        statusDataResponseDTO.setName( model.getName() );
        statusDataResponseDTO.setIsInitial( model.getIsInitial() );
        statusDataResponseDTO.setIsActive( model.getIsActive() );

        return statusDataResponseDTO;
    }

    @Override
    public StatusResponseDTO toBrief(StatusModel model) {
        if ( model == null ) {
            return null;
        }

        StatusResponseDTO statusResponseDTO = new StatusResponseDTO();

        statusResponseDTO.setId( model.getId() );
        statusResponseDTO.setName( model.getName() );
        statusResponseDTO.setIsInitial( model.getIsInitial() );
        statusResponseDTO.setIsActive( model.getIsActive() );

        return statusResponseDTO;
    }
}
