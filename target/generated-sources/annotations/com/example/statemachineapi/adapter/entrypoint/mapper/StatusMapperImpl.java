package com.example.statemachineapi.adapter.entrypoint.mapper;

import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;
import com.example.statemachineapi.domain.model.StatusModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T21:22:01-0300",
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

        return statusResponseDTO;
    }
}
