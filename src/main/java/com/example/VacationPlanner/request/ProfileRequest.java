package com.example.VacationPlanner.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String age;

}
