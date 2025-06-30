package com.example.statemachineapi.adapter.handler;

import com.example.statemachineapi.adapter.entrypoint.dto.error.ErrorDTO;
import com.example.statemachineapi.adapter.entrypoint.dto.error.ErrorResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class EntryPointExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return showMessageErrors(List.of(ErrorResponseDTO.builder()
                .code(status.getReasonPhrase())
                .message(e.getMessage())
                .build()), status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return showMessageErrors(List.of(ErrorResponseDTO.builder()
                .code(status.getReasonPhrase())
                .message("Erro ao processar a solicitação: " + e.getMessage())
                .build()), status);
    }

    private ResponseEntity<ErrorDTO> showMessageErrors(List<ErrorResponseDTO> errorList, HttpStatus statusCode) {
        ErrorDTO errorDto = ErrorDTO.builder()
                .statusCode(String.valueOf(statusCode.value()))
                .errors(errorList)
                .build();
        return new ResponseEntity<>(errorDto, statusCode);
    }
}

