package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.StateMachineModel;
import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.domain.model.TransitionModel;
import com.example.statemachineapi.entrypoint.dto.CreateStateMachineRequestDTO;
import com.example.statemachineapi.entrypoint.dto.StateMachineDataResponseDTO;
import com.example.statemachineapi.entrypoint.dto.StatusDataResponseDTO;
import com.example.statemachineapi.entrypoint.dto.TransitionResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T15:24:30-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class StateMachineDataMapperImpl implements StateMachineDataMapper {

    @Autowired
    private StatusDataMapper statusDataMapper;
    @Autowired
    private TransitionMapper transitionMapper;

    @Override
    public StateMachineModel toModel(CreateStateMachineRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        StateMachineModel.StateMachineModelBuilder stateMachineModel = StateMachineModel.builder();

        stateMachineModel.name( dto.getName() );

        return stateMachineModel.build();
    }

    @Override
    public StateMachineDataResponseDTO toDto(StateMachineModel model) {
        if ( model == null ) {
            return null;
        }

        StateMachineDataResponseDTO stateMachineDataResponseDTO = new StateMachineDataResponseDTO();

        stateMachineDataResponseDTO.setStatuses( statusModelListToStatusDataResponseDTOList( model.getStatuses() ) );
        stateMachineDataResponseDTO.setTransitions( transitionModelListToTransitionResponseDTOList( model.getTransitions() ) );
        stateMachineDataResponseDTO.setId( model.getId() );
        stateMachineDataResponseDTO.setName( model.getName() );
        stateMachineDataResponseDTO.setIsActive( model.getIsActive() );

        return stateMachineDataResponseDTO;
    }

    protected List<StatusDataResponseDTO> statusModelListToStatusDataResponseDTOList(List<StatusModel> list) {
        if ( list == null ) {
            return null;
        }

        List<StatusDataResponseDTO> list1 = new ArrayList<StatusDataResponseDTO>( list.size() );
        for ( StatusModel statusModel : list ) {
            list1.add( statusDataMapper.toDto( statusModel ) );
        }

        return list1;
    }

    protected List<TransitionResponseDTO> transitionModelListToTransitionResponseDTOList(List<TransitionModel> list) {
        if ( list == null ) {
            return null;
        }

        List<TransitionResponseDTO> list1 = new ArrayList<TransitionResponseDTO>( list.size() );
        for ( TransitionModel transitionModel : list ) {
            list1.add( transitionMapper.toDto( transitionModel ) );
        }

        return list1;
    }
}
