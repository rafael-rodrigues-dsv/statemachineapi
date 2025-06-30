package com.example.statemachineapi.adapter.entrypoint.resource;

import com.example.statemachineapi.adapter.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.UpdateEventRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.error.ErrorDTO;
import com.example.statemachineapi.domain.service.EventService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/state-machines/{stateMachineId}/events")
@Tag(name = "Events", description = "Operations related to Events")
@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventResource {

    private final EventService service;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = EventResponseDTO.class)))
    public ResponseEntity<EventResponseDTO> create(@PathVariable("stateMachineId") UUID stateMachineId) {
        EventResponseDTO res = service.create(stateMachineId);
        return ResponseEntity.created(URI.create("/api/state-machines/" + stateMachineId + "/events/" + res.getId())).body(res);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventResponseDTO.class)))
    public ResponseEntity<List<EventResponseDTO>> getAll(@PathVariable("stateMachineId") UUID stateMachineId) {
        List<EventResponseDTO> list = service.getAll(stateMachineId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventResponseDTO.class)))
    public EventResponseDTO get(@PathVariable("stateMachineId") UUID stateMachineId, @PathVariable UUID id) {
        return service.getById(stateMachineId, id);
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventResponseDTO.class)))
    public EventResponseDTO update(@PathVariable("stateMachineId") UUID stateMachineId, @PathVariable("id") UUID id, @RequestBody UpdateEventRequestDTO dto) {
        return service.update(stateMachineId, id, dto);
    }
}