package com.example.VacationPlanner.controller;

import com.example.VacationPlanner.config.JwtService;
import com.example.VacationPlanner.model.VacationAppUser;
import com.example.VacationPlanner.model.VacationPlan;
import com.example.VacationPlanner.model.VacationRequest;
import com.example.VacationPlanner.repository.VacationAppUserRepository;
import com.example.VacationPlanner.repository.VacationPlanRepository;
import com.example.VacationPlanner.repository.VacationRequestRepository;
import com.example.VacationPlanner.request.ChatGPTRequest;
import com.example.VacationPlanner.request.VacationPlanRequest;
import com.example.VacationPlanner.response.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/llm")
public class BotController {

    @Value("${openai.model}")
    private String model;

    @Autowired
    private RestTemplate template;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private VacationAppUserRepository vacationAppUserRepository;

    @Autowired
    private VacationRequestRepository vacationRequestRepository;

    @Autowired
    private VacationPlanRepository vacationPlanRepository;

    @Value("${openai.api.url}")
    private String apiURL;

    @PostMapping("")
    public String chat(@RequestBody VacationPlanRequest vacationPlanRequest, @RequestHeader("Authorization") String authorizationHeader){

        try {
            String token = authorizationHeader.substring(7);
            String username = jwtService.extractUsername(token);

            VacationAppUser user = vacationAppUserRepository.findByUsername(username).get();

            VacationRequest vacationRequest = new VacationRequest(
                    vacationPlanRequest.getDestination(),
                    vacationPlanRequest.getStartDate().toString(),
                    vacationPlanRequest.getEndDate().toString(),
                    String.valueOf(vacationPlanRequest.getPartySize()),
                    vacationPlanRequest.getTripContent(),
                    user
            );
            vacationRequestRepository.save(vacationRequest);


            String prompt = "You are an eccentric tour guide that has travelled the world. Please provide a vacation plan for " +
                    vacationPlanRequest.getPartySize() +
                    " people. The vacation will be in " +
                    vacationPlanRequest.getDestination() +
                    " and will focus on " +
                    vacationPlanRequest.getTripContent() +
                    ". The party will arrive on " +
                    vacationPlanRequest.getStartDate().toString() +
                    " and will be leaving on " +
                    vacationPlanRequest.getEndDate().toString() +
                    ". For each point of interest, provide a Wikipedia link explaining it.";

            ChatGPTRequest request = new ChatGPTRequest(model, prompt);
            ChatGPTResponse chatGptResponse = template.postForObject(apiURL, request, ChatGPTResponse.class);
            assert chatGptResponse != null;
            String vacationPlan = chatGptResponse.getChoices().getFirst().getMessage().getContent();
            VacationPlan plan = new VacationPlan(vacationPlan, vacationRequest);
            vacationPlanRepository.save(plan);
            System.out.println(vacationPlan);
            return vacationPlan;
        } catch (IllegalArgumentException e){
            return "Invalid token format";
        } catch (UsernameNotFoundException e){
            return "User not found";
        } catch (Exception e){
            return "An unexpected error has occurred.";
        }
    }

}
