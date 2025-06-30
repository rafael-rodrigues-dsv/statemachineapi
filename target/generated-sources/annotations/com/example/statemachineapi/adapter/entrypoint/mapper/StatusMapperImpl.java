package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;
import com.example.statemachineapi.domain.model.StatusModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-30T00:34:11-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class StatusMapperImpl implements StatusMapper {

    @Override
    public StatusResponseDTO toDto(StatusModel model) {
        if ( model == null ) {
            return null;
        }

        StatusResponseDTO.StatusResponseDTOBuilder statusResponseDTO = StatusResponseDTO.builder();

        statusResponseDTO.id( model.getId() );
        statusResponseDTO.name( model.getName() );
        statusResponseDTO.isInitial( model.getIsInitial() );

        return statusResponseDTO.build();
    }
}
