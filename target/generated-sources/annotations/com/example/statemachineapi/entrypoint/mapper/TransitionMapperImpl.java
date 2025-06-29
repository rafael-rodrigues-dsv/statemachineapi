package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.TransitionModel;
import com.example.statemachineapi.entrypoint.dto.CreateTransitionRequestDTO;
import com.example.statemachineapi.entrypoint.dto.TransitionResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T15:24:30-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class TransitionMapperImpl implements TransitionMapper {

    @Autowired
    private StateMachineMapper stateMachineMapper;
    @Autowired
    private StatusMapper statusMapper;

    @Override
    public TransitionModel toModel(CreateTransitionRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TransitionModel.TransitionModelBuilder transitionModel = TransitionModel.builder();

        return transitionModel.build();
    }

    @Override
    public TransitionResponseDTO toDto(TransitionModel model) {
        if ( model == null ) {
            return null;
        }

        TransitionResponseDTO transitionResponseDTO = new TransitionResponseDTO();

        transitionResponseDTO.setId( model.getId() );
        transitionResponseDTO.setIsActive( model.getIsActive() );
        transitionResponseDTO.setStateMachine( stateMachineMapper.toDto( model.getStateMachine() ) );
        transitionResponseDTO.setSourceStatus( statusMapper.toDto( model.getSourceStatus() ) );
        transitionResponseDTO.setTargetStatus( statusMapper.toDto( model.getTargetStatus() ) );

        return transitionResponseDTO;
    }
}
