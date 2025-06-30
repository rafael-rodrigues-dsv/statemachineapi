package com.example.statemachineapi.adapter.entrypoint.resource;

import com.example.statemachineapi.adapter.entrypoint.dto.StateMachineResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.error.ErrorDTO;
import com.example.statemachineapi.adapter.entrypoint.mapper.StateMachineMapper;
import com.example.statemachineapi.domain.service.StateMachineService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/state-machines")
@Tag(name = "State Machines", description = "Operations related to State Machines")
@ApiResponse(responseCode = "400", description = "BadRequest", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateMachineResource {

    private final StateMachineService service;
    private final StateMachineMapper mapper;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StateMachineResponseDTO.class))))
    public ResponseEntity<List<StateMachineResponseDTO>> getAll() {
        List<StateMachineResponseDTO> list = service.getAll()
                .stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = StateMachineResponseDTO.class)))
    public StateMachineResponseDTO get(@PathVariable UUID id) {
        return mapper.toDto(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = StateMachineResponseDTO.class)))
    public StateMachineResponseDTO disable(@PathVariable UUID id) {
        return mapper.toDto(service.disable(id));
    }
}