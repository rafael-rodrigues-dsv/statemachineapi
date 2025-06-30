package com.example.statemachineapi.adapter.entrypoint.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ErrorDTO {
    private String statusCode;
    private List<ErrorResponseDTO> errors;
}
