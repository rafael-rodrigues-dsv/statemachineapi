package com.example.statemachineapi.adapter.entrypoint.resource;

import com.example.statemachineapi.domain.service.TransitionService;
import com.example.statemachineapi.adapter.entrypoint.dto.CreateTransitionRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.TransitionResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/state-machines/{stateMachineId}/transitions")
@Tag(name = "Transitions", description = "Operations related to Transitions")
public class TransitionResource {
    @Autowired
    private TransitionService service;

    @PostMapping
    public ResponseEntity<TransitionResponseDTO> create(@PathVariable("stateMachineId") UUID stateMachineId, @RequestBody CreateTransitionRequestDTO dto) {
        TransitionResponseDTO res = service.create(stateMachineId, dto);
        return ResponseEntity.created(URI.create("/api/transitions/" + res.getId())).body(res);
    }

    @GetMapping("/{id}")
    public TransitionResponseDTO get(@PathVariable("stateMachineId") UUID stateMachineId, @PathVariable UUID id) {
        return service.getById(stateMachineId, id);
    }
}