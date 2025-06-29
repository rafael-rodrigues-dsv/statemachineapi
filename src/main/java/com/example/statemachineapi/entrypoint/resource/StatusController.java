package com.example.statemachineapi.entrypoint.resource;

import com.example.statemachineapi.domain.service.StatusService;
import com.example.statemachineapi.entrypoint.dto.CreateStatusRequestDTO;
import com.example.statemachineapi.entrypoint.dto.StatusDataResponseDTO;
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
@RequestMapping("/api/statuses")
public class StatusController {
    @Autowired
    private StatusService service;

    @PostMapping
    public ResponseEntity<StatusDataResponseDTO> create(@RequestBody CreateStatusRequestDTO dto) {
        StatusDataResponseDTO res = service.create(dto);
        return ResponseEntity.created(URI.create("/api/statuses/" + res.getId())).body(res);
    }

    @GetMapping("/{id}")
    public StatusDataResponseDTO get(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}")
    public StatusDataResponseDTO disable(@PathVariable UUID id) {
        return service.disable(id);
    }
}