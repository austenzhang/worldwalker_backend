package com.example.VacationPlanner.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacationPlanRequest {

    private String destination;

    private LocalDate startDate;

    private LocalDate endDate;

    private int partySize;

    private String tripContent;

}
