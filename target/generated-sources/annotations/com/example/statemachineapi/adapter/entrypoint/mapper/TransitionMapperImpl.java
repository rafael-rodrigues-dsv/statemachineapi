package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.TransitionResponseDTO;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.model.TransitionModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-30T21:42:37-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class TransitionMapperImpl implements TransitionMapper {

    @Override
    public TransitionResponseDTO toDto(TransitionModel model) {
        if ( model == null ) {
            return null;
        }

        TransitionResponseDTO.TransitionResponseDTOBuilder transitionResponseDTO = TransitionResponseDTO.builder();

        transitionResponseDTO.id( model.getId() );
        transitionResponseDTO.sourceStatus( statusModelToStatusResponseDTO( model.getSourceStatus() ) );
        transitionResponseDTO.targetStatus( statusModelToStatusResponseDTO( model.getTargetStatus() ) );

        return transitionResponseDTO.build();
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
