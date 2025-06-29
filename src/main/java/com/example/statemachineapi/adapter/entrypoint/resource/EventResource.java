package com.example.statemachineapi.adapter.entrypoint.resource;

import com.example.statemachineapi.adapter.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.UpdateEventRequestDTO;
import com.example.statemachineapi.domain.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/state-machines/{stateMachineId}/events")
@Tag(name = "Events", description = "Operations related to Events")
public class EventResource {
    @Autowired
    private EventService service;

    @PostMapping
    public ResponseEntity<EventResponseDTO> create(@PathVariable("stateMachineId") UUID stateMachineId) {
        EventResponseDTO res = service.create(stateMachineId);
        return ResponseEntity.created(URI.create("/api/state-machines/" + stateMachineId + "/events/" + res.getId())).body(res);
    }

    @GetMapping("/{id}")
    public EventResponseDTO get(@PathVariable("stateMachineId") UUID stateMachineId, @PathVariable("id") UUID id) {
        return service.getById(stateMachineId, id);
    }

    @PatchMapping("/{id}")
    public EventResponseDTO update(@PathVariable("stateMachineId") UUID stateMachineId, @PathVariable("id") UUID id, @RequestBody UpdateEventRequestDTO dto) {
        return service.update(stateMachineId, id, dto);
    }
}