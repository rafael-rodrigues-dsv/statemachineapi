package com.example.statemachineapi.adapter.entrypoint.resource;

import com.example.statemachineapi.adapter.entrypoint.dto.StatusResponseDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.error.ErrorDTO;
import com.example.statemachineapi.domain.service.StatusService;
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
@RequestMapping("/api/state-machines/{stateMachineId}/statuses")
@Tag(name = "Statuses", description = "Operations related to Statuses")
@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatusResource {

    private final StatusService service;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StatusResponseDTO.class))))
    public ResponseEntity<List<StatusResponseDTO>> getAll(@PathVariable("stateMachineId") UUID stateMachineId) {
        List<StatusResponseDTO> list = service.getAll(stateMachineId);
        return ResponseEntity.ok(list);
    }
}