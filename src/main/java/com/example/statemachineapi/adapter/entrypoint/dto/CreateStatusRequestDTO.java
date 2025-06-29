package com.example.statemachineapi.adapter.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateStatusRequestDTO {
    private String name;
    private Boolean isInitial;
}