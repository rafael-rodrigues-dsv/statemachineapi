package com.example.statemachineapi.entrypoint.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateEventRequestDTO {
    private UUID statusId;
}