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
        return showMessageErrors(List.of(ErrorResponseDTO.builder()
                .message(e.getMessage())
                .build()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
        return showMessageErrors(List.of(ErrorResponseDTO.builder()
                .message("Erro ao processar a solicitação: " + e.getMessage())
                .build()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDTO> showMessageErrors(List<ErrorResponseDTO> errorList, HttpStatus statusCode) {
        ErrorDTO errorDto = ErrorDTO.builder()
                .statusCode(statusCode.getReasonPhrase())
                .errors(errorList)
                .build();
        return new ResponseEntity<>(errorDto, statusCode);
    }
}

