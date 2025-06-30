package com.example.statemachineapi.adapter.entrypoint.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponseDTO {
    private String code;
    private String message;
}
