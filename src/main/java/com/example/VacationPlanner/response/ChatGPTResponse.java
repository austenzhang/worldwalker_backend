package com.example.VacationPlanner.response;

import com.example.VacationPlanner.request.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTResponse {

    private List<Choice> choices;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice{

        private int index;
        private Message message;
    }

}
