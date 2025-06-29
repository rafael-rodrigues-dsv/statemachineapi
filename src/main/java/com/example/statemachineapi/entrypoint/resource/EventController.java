package com.example.statemachineapi.entrypoint.resource;

import com.example.statemachineapi.domain.service.EventService;
import com.example.statemachineapi.entrypoint.dto.EventResponseDTO;
import com.example.statemachineapi.entrypoint.dto.UpdateEventRequestDTO;
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
@RequestMapping("/api/state-machine/{smId}/event")
public class EventController {
    @Autowired
    private EventService service;

    @PostMapping
    public ResponseEntity<EventResponseDTO> create(@PathVariable("smId") UUID smId) {
        EventResponseDTO res = service.create(smId);
        return ResponseEntity.created(URI.create("/api/state-machine/" + smId + "/event/" + res.getId())).body(res);
    }

    @GetMapping("/{id}")
    public EventResponseDTO get(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}")
    public EventResponseDTO update(@PathVariable("id") UUID id, @RequestBody UpdateEventRequestDTO dto) {
        return service.update(id, dto);
    }
}