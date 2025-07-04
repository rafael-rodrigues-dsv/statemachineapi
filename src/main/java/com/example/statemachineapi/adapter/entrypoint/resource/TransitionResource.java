package com.example.statemachineapi.adapter.entrypoint.resource;

import com.example.statemachineapi.adapter.entrypoint.dto.TransitionResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.error.ErrorDTO;
import com.example.statemachineapi.adapter.entrypoint.mapper.TransitionMapper;
import com.example.statemachineapi.domain.service.TransitionService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/state-machines/{stateMachineId}/transitions")
@Tag(name = "Transitions", description = "Operations related to Transitions")
@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransitionResource {

    @Autowired
    private final TransitionService service;
    private final TransitionMapper mapper;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransitionResponseDTO.class))))
    public ResponseEntity<List<TransitionResponseDTO>> getAll(@PathVariable("stateMachineId") UUID stateMachineId) {
        List<TransitionResponseDTO> list = service.getAll(stateMachineId)
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(list);
    }
}