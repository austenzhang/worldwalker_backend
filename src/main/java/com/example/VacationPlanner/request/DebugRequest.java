package com.example.VacationPlanner.request;

import jakarta.validation.constraints.NotBlank;

public class DebugRequest {

    @NotBlank(message = "test string required.")
    String test;

}
