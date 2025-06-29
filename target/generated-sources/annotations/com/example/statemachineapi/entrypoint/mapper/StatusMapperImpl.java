package com.example.statemachineapi.entrypoint.mapper;

import com.example.statemachineapi.domain.model.StatusModel;
import com.example.statemachineapi.entrypoint.dto.StatusResponseDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T15:24:30-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class StatusMapperImpl implements StatusMapper {

    @Override
    public StatusResponseDTO toDto(StatusModel model) {
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
