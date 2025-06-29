package com.example.statemachineapi.adapter.entrypoint.resource;

import com.example.statemachineapi.adapter.entrypoint.dto.CreateStatusRequestDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.StatusDataResponseDTO;
import com.example.statemachineapi.domain.service.StatusService;
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
@RequestMapping("/api/state-machines/{stateMachineId}/statuses")
@Tag(name = "Statuses", description = "Operations related to Statuses")
public class StatusResource {
    @Autowired
    private StatusService service;

    @PostMapping
    public ResponseEntity<StatusDataResponseDTO> create(@PathVariable("stateMachineId") UUID stateMachineId, @RequestBody CreateStatusRequestDTO dto) {
        StatusDataResponseDTO res = service.create(stateMachineId, dto);
        return ResponseEntity.created(URI.create("/api/state-machines/" + stateMachineId + "/statuses/" + res.getId())).body(res);
    }

    @GetMapping("/{id}")
    public StatusDataResponseDTO get(@PathVariable("stateMachineId") UUID stateMachineId, @PathVariable UUID id) {
        return service.getById(stateMachineId, id);
    }
}