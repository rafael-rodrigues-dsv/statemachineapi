package com.example.statemachineapi.adapter.entrypoint.resource;

import com.example.statemachineapi.domain.service.StateMachineService;
import com.example.statemachineapi.adapter.entrypoint.dto.CreateStateMachineRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StateMachineDataResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/state-machines")
@Tag(name = "State Machines", description = "Operations related to State Machines")
public class StateMachineResource {
    @Autowired
    private StateMachineService service;

    @GetMapping
    public ResponseEntity<List<StateMachineDataResponseDTO>> getAll() {
        List<StateMachineDataResponseDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<StateMachineDataResponseDTO> create(@RequestBody CreateStateMachineRequestDTO dto) {
        StateMachineDataResponseDTO res = service.create(dto);
        return ResponseEntity.created(URI.create("/api/state-machine/" + res.getId())).body(res);
    }

    @GetMapping("/{id}")
    public StateMachineDataResponseDTO get(@PathVariable UUID id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public StateMachineDataResponseDTO disable(@PathVariable UUID id) {
        return service.disable(id);
    }
}