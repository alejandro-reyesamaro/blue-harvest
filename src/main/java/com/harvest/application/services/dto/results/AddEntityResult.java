package com.harvest.application.services.dto.results;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddEntityResult {
    private boolean isSuccess;
    private int id;
    private String message;
}
